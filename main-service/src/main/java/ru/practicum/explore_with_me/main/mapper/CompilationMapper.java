package ru.practicum.explore_with_me.main.mapper;

import lombok.experimental.UtilityClass;
import ru.practicum.explore_with_me.main.dto.compilation.CompilationDto;
import ru.practicum.explore_with_me.main.model.Compilation;

import java.util.Map;
import java.util.stream.Collectors;

@UtilityClass
public class CompilationMapper {

    public static CompilationDto toDto(
            final Compilation compilation,
            Map<Long, Integer> views
    ) {
        return CompilationDto
                .builder()
                .id(compilation.getId())
                .title(compilation.getName())
                .pinned(compilation.isPinned())
                .events(compilation.getEvents().stream().map(
                        el -> EventMapper.toShortDto(
                                el,
                                views.getOrDefault(el.getId(), 0)
                        )
                ).collect(Collectors.toSet()))
                .build();
    }

}
