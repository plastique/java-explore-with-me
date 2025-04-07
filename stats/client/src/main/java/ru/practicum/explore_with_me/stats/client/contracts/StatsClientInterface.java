package ru.practicum.explore_with_me.stats.client.contracts;

import ru.practicum.explore_with_me.stats.dto.HitAddRequestDto;
import ru.practicum.explore_with_me.stats.dto.HitStatDto;
import ru.practicum.explore_with_me.stats.dto.StatsGetRequestDto;

import java.util.List;

public interface StatsClientInterface {

    void addHit(HitAddRequestDto dto);

    List<HitStatDto> getStats(StatsGetRequestDto dto);
}
