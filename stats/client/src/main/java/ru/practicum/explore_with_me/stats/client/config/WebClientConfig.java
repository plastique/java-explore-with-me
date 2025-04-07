package ru.practicum.explore_with_me.stats.client.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.time.Duration;

@Configuration
public class WebClientConfig {

    private static final int TIMEOUT = 3000;

    @Bean
    public RestTemplate restTemplate(
            RestTemplateBuilder builder,
            @Value("${app.stats-server.url}") String serverUrl
    ) {
        return builder
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .setConnectTimeout(Duration.ofMillis(TIMEOUT))
                .setReadTimeout(Duration.ofMillis(TIMEOUT))
                .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl))
                .build();
    }

}
