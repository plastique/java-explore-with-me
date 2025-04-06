package ru.practicum.explore_with_me.stats.server.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class StatsRequestDto {

    @NotBlank
    private LocalDateTime start;

    @NotBlank
    private LocalDateTime end;

    @NotNull
    private String[] uris;

    private boolean unique;
}
