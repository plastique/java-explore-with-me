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
        StatGetRequestDto request = new StatGetRequestDto();

        request.setStart(LocalDateTime.MIN);
        request.setEnd(LocalDateTime.now());
        request.setUris(uris);
        request.setUnique(false);

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
