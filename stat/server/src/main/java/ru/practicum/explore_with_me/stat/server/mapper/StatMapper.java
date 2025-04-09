package ru.practicum.explore_with_me.stat.server.mapper;

import ru.practicum.explore_with_me.stat.dto.HitStatDto;
import ru.practicum.explore_with_me.stat.server.dto.StatView;

public class StatMapper {

    private StatMapper() {
    }

    public static HitStatDto toStatDto(StatView dto) {
        return HitStatDto.builder()
                         .app(dto.getApp())
                         .uri(dto.getUri())
                         .hits(dto.getHits())
                         .build();
    }

}
