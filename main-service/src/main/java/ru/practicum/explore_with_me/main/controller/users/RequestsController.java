package ru.practicum.explore_with_me.main.controller.users;

import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.explore_with_me.main.dto.user.request.RequestDto;
import ru.practicum.explore_with_me.main.dto.user.request.RequestListDto;

import java.util.List;

@RestController
@RequestMapping("/users/{userId}/requests")
@Validated
public class RequestsController {

    @GetMapping
    public List<RequestListDto> getList(
            @PathVariable @Positive int userId
    ) {
        return List.of();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RequestDto create(
            @PathVariable @Positive int userId,
            @RequestParam @Positive int eventId
    ) {
        return null;
    }

    @PatchMapping("/{requestId}/cancel")
    public RequestDto update(
            @PathVariable @Positive int userId,
            @PathVariable @Positive int requestId
    ) {
        return null;
    }

}
