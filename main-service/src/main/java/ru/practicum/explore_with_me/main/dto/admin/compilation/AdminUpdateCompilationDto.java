package ru.practicum.explore_with_me.main.dto.admin.compilation;

import lombok.Data;

import java.util.Set;

@Data
public class AdminUpdateCompilationDto {

    private String title;

    private Boolean pinned;

    private Set<Long> events;
}
