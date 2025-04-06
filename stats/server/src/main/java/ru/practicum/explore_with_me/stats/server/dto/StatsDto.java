package ru.practicum.explore_with_me.stats.server.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StatsDto {

    @NotBlank
    private String app;

    @NotBlank
    private String uri;

    @NotNull
    private Integer hits;
}
