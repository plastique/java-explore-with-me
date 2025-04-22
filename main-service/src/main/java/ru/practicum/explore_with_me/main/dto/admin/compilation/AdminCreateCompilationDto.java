package ru.practicum.explore_with_me.main.dto.admin.compilation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class AdminCreateCompilationDto {

    @NotBlank
    @Size(min = 1, max = 50)
    private String title;

    private boolean pinned = false;

    private Set<Long> events;

}
