package ru.practicum.explore_with_me.main.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.explore_with_me.main.dto.user.event.UpdateEventRequestsDto;
import ru.practicum.explore_with_me.main.dto.user.request.EventRequestDto;
import ru.practicum.explore_with_me.main.dto.user.request.EventRequestResultDto;
import ru.practicum.explore_with_me.main.exception.DataErrorException;
import ru.practicum.explore_with_me.main.exception.InvalidArgumentException;
import ru.practicum.explore_with_me.main.exception.LogicErrorException;
import ru.practicum.explore_with_me.main.exception.NotFoundException;
import ru.practicum.explore_with_me.main.mapper.EventRequestMapper;
import ru.practicum.explore_with_me.main.model.Event;
import ru.practicum.explore_with_me.main.model.EventRequest;
import ru.practicum.explore_with_me.main.model.EventRequestState;
import ru.practicum.explore_with_me.main.model.EventState;
import ru.practicum.explore_with_me.main.model.User;
import ru.practicum.explore_with_me.main.repository.EventRepository;
import ru.practicum.explore_with_me.main.repository.EventRequestRepository;
import ru.practicum.explore_with_me.main.repository.UserRepository;
import ru.practicum.explore_with_me.main.service.contracts.EventRequestServiceInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EventRequestService implements EventRequestServiceInterface {

    private final EventRequestRepository eventRequestRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    @Override
    public List<EventRequestDto> getRequestsListByEventIdAndUser(Long eventId, Long userId) {

        checkUser(userId);
        checkEvent(eventId, userId);

        return eventRequestRepository.findAllByEvent_Id(eventId)
                .stream()
                .map(EventRequestMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public EventRequestResultDto updateByEventIdAndStatusId(
            Long eventId,
            Long userId,
            UpdateEventRequestsDto dto
    ) {
        checkUser(userId);

        Event event = eventRepository.findByIdAndUser_Id(eventId, userId).orElseThrow(
                () -> new NotFoundException("Event not found")
        );

        if (!event.isRequestModeration() || event.getParticipantLimit() == 0) {
            throw new LogicErrorException("Moderation not required or request list is not limited");
        }

        Map<String, List<EventRequest>> requests = processRequests(
                event,
                dto,
                eventRequestRepository.findAllByEvent_Id(eventId)
        );

        List<EventRequest> confirmed = requests.get("confirmed");
        List<EventRequest> rejected = requests.get("rejected");
        List<EventRequest> pendingLeft = requests.get("pendingLeft");

        eventRequestRepository.saveAll(confirmed);
        eventRequestRepository.saveAll(rejected);

        if (!pendingLeft.isEmpty()) {
            // Если лимит исчерпан, то отменяем оставшиеся заявки
            eventRequestRepository.saveAll(
                    pendingLeft.stream().map(el -> {
                        el.setState(EventRequestState.REJECTED);
                        return el;
                    }).toList()
            );
        }

        event.setConfirmedRequests(
                eventRequestRepository.countByEvent_IdAndState(eventId, EventRequestState.CONFIRMED)
        );
        eventRepository.save(event);

        return new EventRequestResultDto(
                confirmed.stream().map(EventRequestMapper::toDto).toList(),
                rejected.stream().map(EventRequestMapper::toDto).toList()
        );
    }

    @Override
    public List<EventRequestDto> getListByUserId(Long userId) {
        return eventRequestRepository.findAllByUser_Id(userId)
                .stream()
                .map(EventRequestMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public EventRequestDto create(Long eventId, Long userId) {

        if (eventRequestRepository.existsByEvent_IdAndUser_Id(eventId, userId)) {
            throw new LogicErrorException("Event request already exists");
        }

        Event event = eventRepository.findByIdAndUser_Id(eventId, userId).orElseThrow(
                () -> new NotFoundException("Event with id=" + eventId + " not found")
        );

        User user = userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException("User with id=" + userId + " not found")
        );

        if (event.getUser().getId().equals(user.getId())) {
            throw new LogicErrorException("Event owner is same user");
        }

        if (!event.getState().equals(EventState.PUBLISHED)) {
            throw new LogicErrorException("Event is not published");
        }

        if (
                event.isRequestModeration()
                && event.getParticipantLimit() > 0
                && event.getParticipantLimit() <= event.getConfirmedRequests()
        ) {
            throw new LogicErrorException("Participant limit exceeded");
        }

        EventRequest eventRequest = new EventRequest();
        eventRequest.setEvent(event);
        eventRequest.setUser(user);
        eventRequest.setState(
                event.isRequestModeration()
                    ? EventRequestState.PENDING
                    : EventRequestState.CONFIRMED
        );

        try {
            eventRequestRepository.save(eventRequest);
        } catch (DataAccessException e) {
            throw new DataErrorException(e.getMessage());
        }

        return EventRequestMapper.toDto(eventRequest);
    }

    @Override
    @Transactional
    public EventRequestDto cancel(Long id, Long userId) {
        EventRequest eventRequest = eventRequestRepository.findByIdAndUser_Id(id, userId).orElseThrow(
                () -> new NotFoundException("Event request with id=" + id + " not found")
        );
        boolean isConfirmed = eventRequest.getState().equals(EventRequestState.CONFIRMED);

        eventRequest.setState(EventRequestState.CANCELED);
        eventRequestRepository.save(eventRequest);

        if (isConfirmed) {
            updateEventConfirmedCount(eventRequest.getEvent().getId());
        }

        return EventRequestMapper.toDto(eventRequest);
    }

    private void checkUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new NotFoundException("User with id " + userId + " not found");
        }
    }

    private void checkEvent(Long eventId, Long userId) {
        if (!eventRepository.existsByIdAndUser_Id(eventId, userId)) {
            throw new NotFoundException("Event with id " + userId + " not found");
        }
    }

    private Map<String, List<EventRequest>> processRequests(
            Event event,
            UpdateEventRequestsDto dto,
            List<EventRequest> requests
    ) {
        List<EventRequest> confirmed = new ArrayList<>();
        List<EventRequest> rejected = new ArrayList<>();
        List<EventRequest> pendingLeft = new ArrayList<>();

        int limit = event.getParticipantLimit();
        int current = event.getConfirmedRequests();

        if (limit <= current) {
            throw new LogicErrorException("Requests limit exceeded");
        }

        for (EventRequest eventRequest : requests) {
            if (!dto.getRequestIds().contains(eventRequest.getId())) {
                if (eventRequest.getState().equals(EventRequestState.PENDING)) {
                    pendingLeft.add(eventRequest);
                }
                continue;
            }

            if (eventRequest.getState() != EventRequestState.PENDING) {
                throw new InvalidArgumentException("Request #" + eventRequest.getId() + " is not pending");
            }

            if (dto.getStatus().equals(EventRequestState.CONFIRMED) && current < limit) {
                eventRequest.setState(EventRequestState.CONFIRMED);
                confirmed.add(eventRequest);
                current++;
                continue;
            }

            eventRequest.setState(EventRequestState.REJECTED);
            rejected.add(eventRequest);
        }

        return Map.of(
                "confirmed", confirmed,
                "rejected", rejected,
                "pendingLeft", current >= limit ? pendingLeft : List.of()
        );
    }

    private void updateEventConfirmedCount(Long eventId) {
        Event event = eventRepository.findById(eventId).orElseThrow(
                () -> new NotFoundException("Event with id=" + eventId + " not found")
        );

        event.setConfirmedRequests(
                eventRequestRepository.countByEvent_IdAndState(eventId, EventRequestState.CONFIRMED)
        );
        eventRepository.save(event);
    }

}
