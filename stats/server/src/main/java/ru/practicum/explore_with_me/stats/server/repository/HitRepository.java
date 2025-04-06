package ru.practicum.explore_with_me.stats.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.explore_with_me.stats.server.dto.StatsDto;
import ru.practicum.explore_with_me.stats.server.model.Hit;

import java.time.LocalDateTime;
import java.util.List;

public interface HitRepository extends JpaRepository<Hit, Long> {

    @Query(
            "SELECT s.app, s.uri, COUNT(*) hits "
                    + "FROM Hit as s "
                    + "WHERE s.timestamp >= :start AND s.timestamp <= :end "
                    + "GROUP BY s.ip"
    )
    List<StatsDto> findAllByDatesUnique(LocalDateTime start, LocalDateTime end);

    @Query(
            "SELECT s.app, s.uri, CONCAT('1') hits "
                    + "FROM Hit as s "
                    + "WHERE s.timestamp >= :start AND s.timestamp <= :end"
    )
    List<StatsDto> findAllByDates(LocalDateTime start, LocalDateTime end);


    @Query(
            "SELECT s.app, s.uri, COUNT(*) hits "
                    + "FROM Hit as s "
                    + "WHERE s.timestamp >= :start AND s.timestamp <= :end AND s.uri IN (:uris) "
                    + "GROUP BY s.ip"
    )
    List<StatsDto> findAllByDatesAndUriUnique(LocalDateTime start, LocalDateTime end, String[] uris);

    @Query(
            "SELECT s.app, s.uri, COUNT(*) hits "
                    + "FROM Hit as s "
                    + "WHERE s.timestamp >= :start AND s.timestamp <= :end AND s.uri IN (:uris)"
    )
    List<StatsDto> findAllByDatesAndUri(LocalDateTime start, LocalDateTime end, String[] uris);
}
