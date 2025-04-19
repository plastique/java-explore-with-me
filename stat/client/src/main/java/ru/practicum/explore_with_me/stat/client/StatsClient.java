package ru.practicum.explore_with_me.stat.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.practicum.explore_with_me.stat.client.contracts.StatsClientInterface;
import ru.practicum.explore_with_me.stat.dto.StatGetRequestDto;
import ru.practicum.explore_with_me.stat.dto.HitAddRequestDto;
import ru.practicum.explore_with_me.stat.dto.HitStatDto;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class StatsClient implements StatsClientInterface {

    private static final String API_HIT = "/hit";
    private static final String API_STATS = "/stats";

    private final WebClient client;

    StatsClient(
            @Value("${app.stats-server.url}") String serverUri
    ) {
        this.client = WebClient.builder()
                .baseUrl(serverUri)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Override
    public void addHit(HitAddRequestDto dto) {
        client.post()
              .uri(API_HIT)
              .bodyValue(dto)
              .retrieve()
              .toBodilessEntity()
              .onErrorComplete()
              .block();
    }

    @Override
    public List<HitStatDto> getStats(StatGetRequestDto dto) {
        Optional<String> urisOptional = dto.getUris() != null && !dto.getUris().isEmpty()
                ? Optional.of(String.join(",", dto.getUris()))
                : Optional.empty();


        List<HitStatDto> response = client
                .get()
                .uri(
                        builder -> builder.path(API_STATS)
                                          .queryParam("start", dto.getStart())
                                          .queryParam("end", dto.getEnd())
                                          .queryParam("unique", dto.isUnique())
                                          .queryParamIfPresent("uris", urisOptional)
                                          .build()
                )
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<HitStatDto>>() {})
                .onErrorReturn(Collections.emptyList())
                .block();

        return response == null
                ? Collections.emptyList()
                : response;
    }

}
