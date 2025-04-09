package ru.practicum.explore_with_me.stat.server.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class StatRequestDto {

    @NotBlank
    private LocalDateTime start;

    @NotBlank
    private LocalDateTime end;

    @NotNull
    private List<String> uris;

    private boolean unique;
}
