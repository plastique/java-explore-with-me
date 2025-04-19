package ru.practicum.explore_with_me.main.mapper;

import lombok.experimental.UtilityClass;
import ru.practicum.explore_with_me.main.dto.user.request.EventRequestDto;
import ru.practicum.explore_with_me.main.model.EventRequest;

@UtilityClass
public class EventRequestMapper {

    public static EventRequestDto toDto(EventRequest request) {
        return EventRequestDto
                .builder()
                .id(request.getId())
                .requestor(request.getUser().getId())
                .event(request.getEvent().getId())
                .status(request.getState())
                .created(request.getCreated())
                .build();
    }

}
