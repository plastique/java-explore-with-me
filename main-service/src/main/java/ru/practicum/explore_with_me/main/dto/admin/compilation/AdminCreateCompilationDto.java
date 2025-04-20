package ru.practicum.explore_with_me.main.dto.admin.compilation;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Set;

@Data
public class AdminCreateCompilationDto {

    @NotBlank
    private String title;

    private boolean pinned = false;

    private Set<Long> events;

}
