package ru.practicum.explore_with_me.stats.server.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.explore_with_me.stats.dto.HitAddRequestDto;
import ru.practicum.explore_with_me.stats.dto.HitStatDto;
import ru.practicum.explore_with_me.stats.server.dto.StatsRequestDto;
import ru.practicum.explore_with_me.stats.server.service.contracts.StatsServiceInterface;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/")
@Validated
public class StatsController {

    private final StatsServiceInterface statsService;
    private final String dateTimePattern;

    public StatsController(
            StatsServiceInterface statsService,
            @Value("${app.date-time-pattern}") String dateTimePattern
    ) {
        this.statsService = statsService;
        this.dateTimePattern = dateTimePattern;
    }

    @PostMapping("/hit")
    @ResponseStatus(HttpStatus.CREATED)
    public void addHit(@RequestBody @Valid HitAddRequestDto dto) {
        statsService.addHit(dto);
    }

    @GetMapping("/stats")
    @ResponseStatus(HttpStatus.OK)
    public List<HitStatDto> getStats(
            @RequestParam @NotBlank String start,
            @RequestParam @NotBlank String end,
            @RequestParam(required = false) List<String> uris,
            @RequestParam(required = false, defaultValue = "false") boolean unique
    ) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateTimePattern);

        return statsService.getStats(
                new StatsRequestDto(
                        LocalDateTime.parse(
                                URLDecoder.decode(start, StandardCharsets.UTF_8),
                                formatter
                        ),
                        LocalDateTime.parse(
                                URLDecoder.decode(end, StandardCharsets.UTF_8),
                                formatter
                        ),
                        uris,
                        unique
                )
        );
    }

}
