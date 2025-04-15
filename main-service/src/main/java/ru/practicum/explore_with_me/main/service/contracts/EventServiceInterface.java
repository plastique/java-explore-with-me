package ru.practicum.explore_with_me.main.service.contracts;

import ru.practicum.explore_with_me.main.dto.event.EventDto;
import ru.practicum.explore_with_me.main.dto.event.SearchEventDto;

import java.util.List;

public interface EventServiceInterface {

    List<EventDto> getList(SearchEventDto searchDto);

    EventDto getById(Long id);
}
