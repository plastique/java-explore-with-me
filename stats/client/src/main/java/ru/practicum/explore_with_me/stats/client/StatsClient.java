package ru.practicum.explore_with_me.stats.client;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import ru.practicum.explore_with_me.stats.client.contracts.StatsClientInterface;
import ru.practicum.explore_with_me.stats.dto.StatsGetRequestDto;
import ru.practicum.explore_with_me.stats.dto.HitAddRequestDto;
import ru.practicum.explore_with_me.stats.dto.HitStatDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StatsClient implements StatsClientInterface {

    private static final String API_HIT = "/hit";
    private static final String API_STATS = "/stats";

    @Autowired
    private RestTemplate webClient;

    @Override
    public void addHit(HitAddRequestDto dto) {
        try {
            webClient.exchange(
                    API_HIT,
                    HttpMethod.POST,
                    new HttpEntity<>(dto),
                    Void.class
            );
        } catch (HttpStatusCodeException ignored) {
        }
    }

    @Override
    public List<HitStatDto> getStats(StatsGetRequestDto dto) {

        StringBuilder url = new StringBuilder(API_STATS + "/?start={start}&end={end}&unique={unique}");
        Map<String, Object> params = new HashMap<>();

        params.put("start", dto.getStart());
        params.put("end", dto.getEnd());
        params.put("unique", dto.isUnique());

        if (dto.getUris() != null && dto.getUris().length > 0) {
            params.put("uris", dto.getUris());
            url.append("&uris={uris}");
        }

        try {
            ResponseEntity<List<HitStatDto>> response = webClient.exchange(
                    url.toString(),
                    HttpMethod.GET,
                    new HttpEntity<>(null, null),
                    new ParameterizedTypeReference<>() {},
                    params
            );

            return response.getBody();
        } catch (HttpStatusCodeException e) {
            return List.of();
        }
    }

}
