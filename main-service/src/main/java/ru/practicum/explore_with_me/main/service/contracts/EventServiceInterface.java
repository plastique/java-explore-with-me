package ru.practicum.explore_with_me.main.service.contracts;

import ru.practicum.explore_with_me.main.dto.admin.event.AdminSearchEventDto;
import ru.practicum.explore_with_me.main.dto.admin.event.AdminUpdateEventDto;
import ru.practicum.explore_with_me.main.dto.event.EventDto;
import ru.practicum.explore_with_me.main.dto.event.SearchEventDto;
import ru.practicum.explore_with_me.main.dto.user.event.UserCreateEventDto;
import ru.practicum.explore_with_me.main.dto.user.event.UserUpdateEventDto;

import java.util.List;

public interface EventServiceInterface {

    List<EventDto> getList(SearchEventDto searchDto);

    List<EventDto> getListForAdmin(AdminSearchEventDto searchDto);

    List<EventDto> getListByUser(Long userId, int from, int size);

    EventDto getById(Long id);

    EventDto getByUserAndId(Long userId, Long id);

    EventDto updateByAdmin(Long id, AdminUpdateEventDto dto);

    EventDto createByUser(Long userId, UserCreateEventDto dto);

    EventDto updateByUser(Long id, Long userId, UserUpdateEventDto dto);

}
