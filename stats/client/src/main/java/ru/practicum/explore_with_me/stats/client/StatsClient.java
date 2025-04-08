package ru.practicum.explore_with_me.stats.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.reactive.function.client.WebClient;
import ru.practicum.explore_with_me.stats.client.contracts.StatsClientInterface;
import ru.practicum.explore_with_me.stats.dto.StatsGetRequestDto;
import ru.practicum.explore_with_me.stats.dto.HitAddRequestDto;
import ru.practicum.explore_with_me.stats.dto.HitStatDto;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class StatsClient implements StatsClientInterface {

    private static final String API_HIT = "/hit";
    private static final String API_STATS = "/stats";

    private final WebClient client;

    StatsClient(
            //WebClient client,
            @Value("${app.stats-server.url}") String serverUri
    ) {
        this.client = WebClient
                .builder()
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
              .doOnError(throwable -> log.error("Error adding hit {}", dto, throwable))
              .block();

        log.info("Added hit {}", dto);
    }

    @Override
    public List<HitStatDto> getStats(StatsGetRequestDto dto) {
        Optional<String> urisOptional = dto.getUris() != null && !dto.getUris().isEmpty()
                ? Optional.of(String.join(",", dto.getUris()))
                : Optional.empty();

        try {
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
                    .block();

            return response == null
                    ? Collections.emptyList()
                    : response;
        } catch (RestClientException e) {
            return Collections.emptyList();
        }
    }

}
