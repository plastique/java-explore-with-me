package ru.practicum.explore_with_me.stat.server.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.explore_with_me.stat.constant.Constants;
import ru.practicum.explore_with_me.stat.dto.HitAddRequestDto;
import ru.practicum.explore_with_me.stat.dto.HitStatDto;
import ru.practicum.explore_with_me.stat.server.dto.StatRequestDto;
import ru.practicum.explore_with_me.stat.server.service.contracts.StatsServiceInterface;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@Validated
public class StatsController {

    private final StatsServiceInterface statsService;

    @PostMapping("/hit")
    @ResponseStatus(HttpStatus.CREATED)
    public void addHit(@RequestBody @Valid HitAddRequestDto dto) {
        statsService.addHit(dto);
    }

    @GetMapping("/stats")
    @ResponseStatus(HttpStatus.OK)
    public List<HitStatDto> getStats(
            @RequestParam @NotNull @DateTimeFormat(pattern = Constants.DATE_TIME_FORMAT) LocalDateTime start,
            @RequestParam @NotNull @DateTimeFormat(pattern = Constants.DATE_TIME_FORMAT) LocalDateTime end,
            @RequestParam(required = false) List<String> uris,
            @RequestParam(required = false, defaultValue = "false") boolean unique
    ) {
        return statsService.getStats(
                new StatRequestDto(start, end, uris, unique)
        );
    }

}
