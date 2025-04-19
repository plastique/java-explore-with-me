package ru.practicum.explore_with_me.main.controller.users;

import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
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
import ru.practicum.explore_with_me.main.dto.user.request.EventRequestDto;
import ru.practicum.explore_with_me.main.service.contracts.EventRequestServiceInterface;

import java.util.List;

@RestController
@RequestMapping("/users/{userId}/requests")
@Validated
@RequiredArgsConstructor
public class UserRequestsController {

    private final EventRequestServiceInterface eventRequestService;

    @GetMapping
    public List<EventRequestDto> getList(
            @PathVariable @Positive Long userId
    ) {
        return eventRequestService.getListByUserId(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EventRequestDto create(
            @PathVariable @Positive Long userId,
            @RequestParam @Positive Long eventId
    ) {
        return eventRequestService.create(eventId, userId);
    }

    @PatchMapping("/{requestId}/cancel")
    public EventRequestDto update(
            @PathVariable @Positive Long userId,
            @PathVariable @Positive Long requestId
    ) {
        return eventRequestService.cancel(requestId, userId);
    }

}
