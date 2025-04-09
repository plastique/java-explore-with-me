package ru.practicum.explore_with_me.stat.server.service.contracts;

import ru.practicum.explore_with_me.stat.dto.HitAddRequestDto;
import ru.practicum.explore_with_me.stat.dto.HitStatDto;
import ru.practicum.explore_with_me.stat.server.dto.StatRequestDto;

import java.util.List;

public interface StatsServiceInterface {

    void addHit(HitAddRequestDto hitDto);

    List<HitStatDto> getStats(StatRequestDto paramsDto);

}
