package ru.practicum.explore_with_me.main.controller.users;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.explore_with_me.main.constant.Constant;
import ru.practicum.explore_with_me.main.dto.user.event.CreateEventDto;
import ru.practicum.explore_with_me.main.dto.user.event.EventDto;
import ru.practicum.explore_with_me.main.dto.user.event.EventListDto;
import ru.practicum.explore_with_me.main.dto.user.event.EventRequestsListDto;
import ru.practicum.explore_with_me.main.dto.user.event.UpdateEventDto;
import ru.practicum.explore_with_me.main.dto.user.event.UpdateEventRequestsDto;

import java.util.List;

@RestController
@RequestMapping("/users/{userId}/events")
@Validated
public class EventsController {

    @GetMapping
    public List<EventListDto> getList(
            @PathVariable @Positive int userId,
            @RequestParam(value = "from", defaultValue = Constant.INT_MIN_STRING) @PositiveOrZero int from,
            @RequestParam(value = "size", defaultValue = "10") @Positive int size
    ) {
        return List.of();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EventDto create(
            @PathVariable @Positive int userId,
            @RequestBody @Valid CreateEventDto dto
    ) {
        return null;
    }

    @GetMapping("/{eventId}")
    @ResponseStatus(HttpStatus.CREATED)
    public EventDto getById(
            @PathVariable @Positive int userId,
            @PathVariable @Positive int eventId
    ) {
        return null;
    }

    @PatchMapping("/{eventId}")
    public EventDto update(
            @PathVariable @Positive int userId,
            @PathVariable @Positive int eventId,
            @RequestBody @Valid UpdateEventDto dto
    ) {
        return null;
    }

    @GetMapping("/{eventId}/requests")
    public List<EventRequestsListDto> getRequestsList(
            @PathVariable @Positive int userId,
            @PathVariable @Positive int eventId
    ) {
        return List.of();
    }

    @PatchMapping("/{eventId}/requests")
    public EventDto update(
            @PathVariable @Positive int userId,
            @PathVariable @Positive int eventId,
            @RequestBody @Valid UpdateEventRequestsDto dto
    ) {
        return null;
    }

}
