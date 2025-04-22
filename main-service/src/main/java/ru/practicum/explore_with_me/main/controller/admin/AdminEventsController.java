package ru.practicum.explore_with_me.main.controller.admin;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.explore_with_me.main.dto.admin.event.AdminSearchEventDto;
import ru.practicum.explore_with_me.main.dto.admin.event.AdminUpdateEventDto;
import ru.practicum.explore_with_me.main.dto.event.EventDto;
import ru.practicum.explore_with_me.main.service.contracts.EventServiceInterface;

import java.util.List;

@RestController
@RequestMapping("/admin/events")
@Validated
@RequiredArgsConstructor
public class AdminEventsController {

    private final EventServiceInterface eventService;

    @GetMapping
    public List<EventDto> getList(
            @Valid AdminSearchEventDto searchDto
    ) {
        return eventService.getListForAdmin(searchDto);
    }

    @PatchMapping("/{eventId}")
    public EventDto update(
            @PathVariable @Positive Long eventId,
            @RequestBody @Valid AdminUpdateEventDto dto
    ) {
        return eventService.updateByAdmin(eventId, dto);
    }

}
