package ru.practicum.explore_with_me.main.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.explore_with_me.main.constant.Constant;
import ru.practicum.explore_with_me.main.constant.Sort;
import ru.practicum.explore_with_me.main.dto.event.EventDto;
import ru.practicum.explore_with_me.main.dto.event.SearchEventDto;
import ru.practicum.explore_with_me.main.service.contracts.EventServiceInterface;
import ru.practicum.explore_with_me.main.service.contracts.StatsServiceInterface;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/events")
@Validated
@RequiredArgsConstructor
public class EventsController {

    private final EventServiceInterface eventService;
    private final StatsServiceInterface statsService;

    @GetMapping
    public List<EventDto> getList(
            @RequestParam(value = "text", required = false) String text,
            @RequestParam(value = "categories", required = false) List<Long> categories,
            @RequestParam(value = "paid", defaultValue = "false") boolean paid,
            @RequestParam(value = "rangeStart", required = false) @DateTimeFormat(pattern = Constant.DATE_TIME_FORMAT) LocalDateTime rangeStart,
            @RequestParam(value = "rangeEnd", required = false) @DateTimeFormat(pattern = Constant.DATE_TIME_FORMAT) LocalDateTime rangeEnd,
            @RequestParam(value = "onlyAvailable", defaultValue = "false") boolean onlyAvailable,
            @RequestParam(value = "sort", required = false) Sort sort,
            @RequestParam(value = "from", defaultValue = Constant.INT_MIN_STRING) @PositiveOrZero int from,
            @RequestParam(value = "size", defaultValue = "10") @Positive int size,
            HttpServletRequest request
    ) {
        List<EventDto> res = eventService.getList(
                SearchEventDto
                        .builder()
                        .text(text)
                        .categories(new HashSet<>(categories))
                        .paid(paid)
                        .rangeStart(rangeStart)
                        .rangeEnd(rangeEnd)
                        .onlyAvailable(onlyAvailable)
                        .sort(sort == null ? Sort.ID : sort)
                        .from(from)
                        .size(size)
                        .build()
        );

        statsService.addHit(request.getRequestURI(), request.getRemoteAddr());

        return res;
    }

    @GetMapping("/{id}")
    public EventDto getById(@PathVariable Long id, HttpServletRequest request) {
        EventDto res = eventService.getById(id);

        statsService.addHit(request.getRequestURI(), request.getRemoteAddr());

        return res;
    }

}
