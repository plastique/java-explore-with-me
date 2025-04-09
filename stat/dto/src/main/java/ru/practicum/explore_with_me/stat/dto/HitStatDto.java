package ru.practicum.explore_with_me.stat.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HitStatDto {

    @NotBlank
    private String app;

    @NotBlank
    private String uri;

    @NotNull
    private Integer hits;

}
