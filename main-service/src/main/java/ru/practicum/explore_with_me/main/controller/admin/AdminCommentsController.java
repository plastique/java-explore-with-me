package ru.practicum.explore_with_me.main.controller.admin;

import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.explore_with_me.main.service.contracts.CommentServiceInterface;

@RestController
@RequestMapping("/admin/comments")
@Validated
@RequiredArgsConstructor
public class AdminCommentsController {

    private final CommentServiceInterface commentService;

    @DeleteMapping("/{id}")
    public void delete(@PathVariable @Positive final Long id) {
        commentService.deleteByAdmin(id);
    }

}
