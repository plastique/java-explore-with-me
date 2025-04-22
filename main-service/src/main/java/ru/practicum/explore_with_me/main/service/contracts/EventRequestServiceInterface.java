package ru.practicum.explore_with_me.main.service.contracts;

import ru.practicum.explore_with_me.main.dto.user.event.UpdateEventRequestsDto;
import ru.practicum.explore_with_me.main.dto.user.request.EventRequestDto;
import ru.practicum.explore_with_me.main.dto.user.request.EventRequestResultDto;

import java.util.List;

public interface EventRequestServiceInterface {

    List<EventRequestDto> getRequestsListByEventIdAndUser(Long eventId, Long userId);

    EventRequestResultDto updateByEventIdAndStatusId(Long eventId, Long userId, UpdateEventRequestsDto dto);

    List<EventRequestDto> getListByUserId(Long userId);

    EventRequestDto create(Long eventId, Long userId);

    EventRequestDto cancel(Long id, Long userId);

}
