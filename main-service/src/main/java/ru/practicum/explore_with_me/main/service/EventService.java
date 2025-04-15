package ru.practicum.explore_with_me.main.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.practicum.explore_with_me.main.dto.event.EventDto;
import ru.practicum.explore_with_me.main.dto.event.SearchEventDto;
import ru.practicum.explore_with_me.main.exception.NotFoundException;
import ru.practicum.explore_with_me.main.mapper.EventMapper;
import ru.practicum.explore_with_me.main.model.Event;
import ru.practicum.explore_with_me.main.model.EventState;
import ru.practicum.explore_with_me.main.model.RequestState;
import ru.practicum.explore_with_me.main.repository.EventRepository;
import ru.practicum.explore_with_me.main.repository.RequestRepository;
import ru.practicum.explore_with_me.main.service.contracts.EventServiceInterface;
import ru.practicum.explore_with_me.main.service.contracts.StatsServiceInterface;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class EventService implements EventServiceInterface {

    public static final String EVENT_ROUTE_PATH = "/events/";

    private final EventRepository eventRepository;
    private final StatsServiceInterface statsService;
    private final RequestRepository requestRepository;

    @Override
    public List<EventDto> getList(SearchEventDto searchDto) {
        Pageable pageable = PageRequest.of(searchDto.getFrom(), searchDto.getSize());
        Specification<Event> specification = Specification.where(null);

        List<Event> events = eventRepository.findAll(specification, pageable).getContent();

        if (events.isEmpty()) {
            return Collections.emptyList();
        }

        Set<String> uris = new HashSet<>();
        Set<Long> ids = new HashSet<>();

        for (Event event : events) {
            uris.add(EVENT_ROUTE_PATH + event.getId());
            ids.add(event.getId());
        }
        Map<String, Integer> views = statsService.getViewsCount(uris);
        Map<Long, Integer> confirmedRequests = requestRepository.countByEvent_IdInAndState(ids, RequestState.CONFIRMED);

        return events.stream().map(event -> EventMapper.toDto(
                event,
                confirmedRequests.getOrDefault(event.getId(), 0),
                views.getOrDefault(EVENT_ROUTE_PATH + event.getId(), 0)
        )).toList();
    }

    @Override
    public EventDto getById(Long id) {
        Event event = eventRepository.findByIdAndState(id, EventState.PUBLISHED).orElseThrow(
                () -> new NotFoundException("Event with id " + id + " not found")
        );

        String uri = EVENT_ROUTE_PATH + event.getId();

        return EventMapper.toDto(
                event,
                requestRepository.countByEvent_IdAndState(
                        event.getId(),
                        RequestState.CONFIRMED
                ),
                statsService.getViewsCount(Set.of(uri)).getOrDefault(uri, 0)
        );
    }

}
