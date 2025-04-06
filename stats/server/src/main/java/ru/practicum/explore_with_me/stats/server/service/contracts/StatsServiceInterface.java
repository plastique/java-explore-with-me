package ru.practicum.explore_with_me.stats.server.service.contracts;

import ru.practicum.explore_with_me.stats.dto.HitAddRequestDto;
import ru.practicum.explore_with_me.stats.dto.HitStatDto;
import ru.practicum.explore_with_me.stats.server.dto.StatsRequestDto;

import java.util.List;

public interface StatsServiceInterface {

    void addHit(HitAddRequestDto hitDto);

    List<HitStatDto> getStats(StatsRequestDto paramsDto);

}
