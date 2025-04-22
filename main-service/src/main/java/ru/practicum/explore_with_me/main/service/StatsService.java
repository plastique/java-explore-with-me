package ru.practicum.explore_with_me.main.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import ru.practicum.explore_with_me.main.service.contracts.StatsServiceInterface;
import ru.practicum.explore_with_me.stat.client.contracts.StatsClientInterface;
import ru.practicum.explore_with_me.stat.dto.HitAddRequestDto;
import ru.practicum.explore_with_me.stat.dto.HitStatDto;
import ru.practicum.explore_with_me.stat.dto.StatGetRequestDto;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@ComponentScan(basePackages = "ru.practicum")
public class StatsService implements StatsServiceInterface {

    private static final LocalDateTime DATE_TIME_MIN = LocalDateTime.of(1970, 1, 1, 0, 0);

    private final StatsClientInterface client;
    private final String appName;

    StatsService(
            StatsClientInterface client,
            @Value("${app.name}")
            String appName
    ) {
        this.client = client;
        this.appName = appName;
    }

    public Map<String, Integer> getViewsCount(Set<String> uris) {
        return getCount(uris, false);
    }

    public Map<String, Integer> getUniqueViewsCount(Set<String> uris) {
        return getCount(uris, true);
    }

    private Map<String, Integer> getCount(Set<String> uris, boolean unique) {
        StatGetRequestDto request = new StatGetRequestDto();

        request.setStart(DATE_TIME_MIN);
        request.setEnd(LocalDateTime.now());
        request.setUris(uris);
        request.setUnique(unique);

        List<HitStatDto> hits = client.getStats(request);
        Map<String, Integer> hitMap = new HashMap<>();

        for (HitStatDto hitStat : hits) {
            hitMap.put(hitStat.getUri(), hitStat.getHits());
        }

        return hitMap;
    }

    public void addHit(String uri, String ip) {
        HitAddRequestDto request = new HitAddRequestDto();
        request.setUri(uri);
        request.setApp(appName);
        request.setIp(ip);
        request.setTimestamp(LocalDateTime.now());

        client.addHit(request);
    }

}
