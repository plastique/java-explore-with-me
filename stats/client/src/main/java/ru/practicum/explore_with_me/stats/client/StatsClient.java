package ru.practicum.explore_with_me.stats.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.practicum.explore_with_me.stats.client.contracts.StatsClientInterface;

@Service
public class StatsClient implements StatsClientInterface {

    private RestTemplate restTemplate;

    StatsClient(@Value("${app.stats-server.url}") String statsServerUrl) {

    }

}
