package ru.practicum.explore_with_me.main.controller;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.explore_with_me.main.constant.Constant;
import ru.practicum.explore_with_me.main.dto.comment.CommentDto;
import ru.practicum.explore_with_me.main.service.CommentService;
import ru.practicum.explore_with_me.main.service.contracts.CommentServiceInterface;

import java.util.List;

@RestController
@RequestMapping("/events/{eventId}/comments")
@Validated
@RequiredArgsConstructor
public class CommentsController {

    private final CommentServiceInterface commentServiceInterface;
    private final CommentService commentService;

    @GetMapping
    public List<CommentDto> getEventComments(
            @PathVariable @Positive Long eventId,
            @RequestParam(value = "from", defaultValue = Constant.INT_MIN_STRING) @PositiveOrZero int from,
            @RequestParam(value = "size", defaultValue = "10") @Positive int size
    ) {
        return commentService.getListByEvent(eventId, from, size);
    }

}
