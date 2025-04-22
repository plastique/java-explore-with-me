package ru.practicum.explore_with_me.main.service.contracts;

import java.util.Map;
import java.util.Set;

public interface StatsServiceInterface {

    Map<String, Integer> getViewsCount(Set<String> uris);

    Map<String, Integer> getUniqueViewsCount(Set<String> uris);

    void addHit(String uri, String ip);

}
