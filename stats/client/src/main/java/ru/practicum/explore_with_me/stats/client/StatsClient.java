package ru.practicum.explore_with_me.stats.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import ru.practicum.explore_with_me.stats.client.contracts.StatsClientInterface;
import ru.practicum.explore_with_me.stats.dto.StatsGetRequestDto;
import ru.practicum.explore_with_me.stats.dto.HitAddRequestDto;
import ru.practicum.explore_with_me.stats.dto.HitStatDto;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class StatsClient implements StatsClientInterface {

    private static final String API_HIT = "/hit";
    private static final String API_STATS = "/stats";

    private final RestClient client;

    StatsClient(
            RestClient client,
            @Value("${app.stats-server.url}") String serverUri
    ) {
        this.client = client.mutate()
                            .baseUrl(serverUri)
                            .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                            .build();
    }

    @Override
    public void addHit(HitAddRequestDto dto) {
        try {
            client.post()
                  .uri(API_HIT)
                  .body(dto)
                  .retrieve()
                  .toBodilessEntity();
        } catch (RestClientException ignored) {
        }
    }

    @Override
    public List<HitStatDto> getStats(StatsGetRequestDto dto) {
        Optional<String[]> urisOptional = dto.getUris() != null && dto.getUris().length > 0
                ? Optional.of(dto.getUris())
                : Optional.empty();

        try {
            ResponseEntity<List<HitStatDto>> response = client.get()
                                                              .uri(builder -> builder.path(API_STATS)
                                                                                     .queryParam("start", dto.getStart())
                                                                                     .queryParam("end", dto.getEnd())
                                                                                     .queryParam("unique", dto.isUnique())
                                                                                     .queryParamIfPresent("uris", urisOptional)
                                                                                     .build()
                                                              )
                                                              .retrieve()
                                                              .body(new ParameterizedTypeReference<>() {});

            return response.getBody();
        } catch (RestClientException e) {
            return Collections.emptyList();
        }
    }

}
