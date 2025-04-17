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
import ru.practicum.explore_with_me.main.dto.user.request.ParticipationRequestDto;

import java.util.List;

@RestController
@RequestMapping("/users/{userId}/requests")
@Validated
public class UserRequestsController {

    @GetMapping
    public List<ParticipationRequestDto> getList(
            @PathVariable @Positive Long userId
    ) {
        return List.of();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ParticipationRequestDto create(
            @PathVariable @Positive Long userId,
            @RequestParam @Positive Long eventId
    ) {
        return null;
    }

    @PatchMapping("/{requestId}/cancel")
    public ParticipationRequestDto update(
            @PathVariable @Positive Long userId,
            @PathVariable @Positive Long requestId
    ) {
        return null;
    }

}
