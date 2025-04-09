package ru.practicum.explore_with_me.stat.client.contracts;

import ru.practicum.explore_with_me.stat.dto.HitAddRequestDto;
import ru.practicum.explore_with_me.stat.dto.HitStatDto;
import ru.practicum.explore_with_me.stat.dto.StatGetRequestDto;

import java.util.List;

public interface StatsClientInterface {

    void addHit(HitAddRequestDto dto);

    List<HitStatDto> getStats(StatGetRequestDto dto);
}
