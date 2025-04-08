package ru.practicum.explore_with_me.stats.server.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.explore_with_me.stats.dto.HitAddRequestDto;
import ru.practicum.explore_with_me.stats.dto.HitStatDto;
import ru.practicum.explore_with_me.stats.server.dto.StatsRequestDto;
import ru.practicum.explore_with_me.stats.server.mapper.StatMapper;
import ru.practicum.explore_with_me.stats.server.model.Hit;
import ru.practicum.explore_with_me.stats.server.repository.HitRepository;
import ru.practicum.explore_with_me.stats.server.service.contracts.StatsServiceInterface;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatsService implements StatsServiceInterface {

    private final HitRepository hitRepository;

    @Override
    @Transactional
    public void addHit(HitAddRequestDto hitDto) {
        Hit hit = new Hit();
        hit.setApp(hitDto.getApp());
        hit.setIp(hitDto.getIp());
        hit.setUri(hitDto.getUri());
        hit.setTimestamp(hitDto.getTimestamp());

        hitRepository.save(hit);
    }

    @Override
    @Transactional(readOnly = true)
    public List<HitStatDto> getStats(StatsRequestDto paramsDto) {

        if (paramsDto.getUris() == null || paramsDto.getUris().isEmpty()) {
            return (paramsDto.isUnique()
                    ? hitRepository.getStatsByDatesUnique(paramsDto.getStart(), paramsDto.getEnd())
                    : hitRepository.getStatsByDates(paramsDto.getStart(), paramsDto.getEnd()))
                    .stream()
                    .map(StatMapper::toStatDto)
                    .toList();
        }

        return (paramsDto.isUnique()
                ? hitRepository.getStatsByDatesAndUriUnique(paramsDto.getStart(), paramsDto.getEnd(), paramsDto.getUris())
                : hitRepository.getStatsByDatesAndUri(paramsDto.getStart(), paramsDto.getEnd(), paramsDto.getUris()))
                .stream()
                .map(StatMapper::toStatDto)
                .toList();
    }

}
