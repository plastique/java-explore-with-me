package ru.practicum.explore_with_me.stats.server.mapper;

import ru.practicum.explore_with_me.stats.dto.HitStatDto;
import ru.practicum.explore_with_me.stats.server.dto.StatsView;

public class StatMapper {

    private StatMapper() {
    }

    public static HitStatDto toStatDto(StatsView dto) {
        return HitStatDto.builder()
                         .app(dto.getApp())
                         .uri(dto.getUri())
                         .hits(dto.getHits())
                         .build();
    }

}
