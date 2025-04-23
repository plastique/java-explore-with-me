package ru.practicum.explore_with_me.main.controller.users;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import ru.practicum.explore_with_me.main.dto.comment.CommentDto;
import ru.practicum.explore_with_me.main.dto.event.EventDto;
import ru.practicum.explore_with_me.main.dto.user.event.UserCreateCommentDto;
import ru.practicum.explore_with_me.main.dto.user.event.UserCreateEventDto;
import ru.practicum.explore_with_me.main.dto.user.event.UserUpdateCommentDto;
import ru.practicum.explore_with_me.main.dto.user.event.UserUpdateEventDto;
import ru.practicum.explore_with_me.main.dto.user.event.UpdateEventRequestsDto;
import ru.practicum.explore_with_me.main.dto.user.request.EventRequestResultDto;
import ru.practicum.explore_with_me.main.dto.user.request.EventRequestDto;
import ru.practicum.explore_with_me.main.service.contracts.CommentServiceInterface;
import ru.practicum.explore_with_me.main.service.contracts.EventRequestServiceInterface;
import ru.practicum.explore_with_me.main.service.contracts.EventServiceInterface;

import java.util.List;

@RestController
@RequestMapping("/users/{userId}/events")
@Validated
@RequiredArgsConstructor
public class UserEventsController {

    private final EventServiceInterface eventService;
    private final EventRequestServiceInterface eventRequestService;
    private final CommentServiceInterface commentService;

    @GetMapping
    public List<EventDto> getList(
            @PathVariable @Positive Long userId,
            @RequestParam(value = "from", defaultValue = Constant.INT_MIN_STRING) @PositiveOrZero int from,
            @RequestParam(value = "size", defaultValue = "10") @Positive int size
    ) {
        return eventService.getListByUser(userId, from, size);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EventDto create(
            @PathVariable @Positive Long userId,
            @RequestBody @Valid UserCreateEventDto dto
    ) {
        return eventService.createByUser(userId, dto);
    }

    @GetMapping("/{eventId}")
    public EventDto getById(
            @PathVariable @Positive Long userId,
            @PathVariable @Positive Long eventId
    ) {
        return eventService.getByIdAndUser(eventId, userId);
    }

    @PatchMapping("/{eventId}")
    public EventDto update(
            @PathVariable @Positive Long userId,
            @PathVariable @Positive Long eventId,
            @RequestBody @Valid UserUpdateEventDto dto
    ) {
        return eventService.updateByUser(eventId, userId, dto);
    }

    @GetMapping("/{eventId}/requests")
    public List<EventRequestDto> getRequestsList(
            @PathVariable @Positive Long userId,
            @PathVariable @Positive Long eventId
    ) {
        return eventRequestService.getRequestsListByEventIdAndUser(eventId, userId);
    }

    @PatchMapping("/{eventId}/requests")
    public EventRequestResultDto update(
            @PathVariable @Positive Long userId,
            @PathVariable @Positive Long eventId,
            @RequestBody @Valid UpdateEventRequestsDto dto
    ) {
        return eventRequestService.updateByEventIdAndStatusId(eventId, userId, dto);
    }

    @PostMapping("/{eventId}/comments")
    public CommentDto addComment(
            @PathVariable @Positive Long userId,
            @PathVariable @Positive Long eventId,
            @RequestBody @Valid UserCreateCommentDto dto
    ) {
        return commentService.create(eventId, userId, dto);
    }

    @PatchMapping("/{eventId}/comments/{commentId}")
    public CommentDto updateComment(
            @PathVariable @Positive Long userId,
            @PathVariable @Positive Long eventId,
            @PathVariable @Positive Long commentId,
            @RequestBody @Valid UserUpdateCommentDto dto
    ) {
        dto.setId(commentId);

        return commentService.update(eventId, userId, dto);
    }

    @DeleteMapping("/{eventId}/comments/{commentId}")
    public void deleteComment(
            @PathVariable @Positive Long userId,
            @PathVariable @Positive Long eventId,
            @PathVariable @Positive Long commentId
    ) {
        commentService.deleteByAuthor(eventId, userId, commentId);
    }

}
