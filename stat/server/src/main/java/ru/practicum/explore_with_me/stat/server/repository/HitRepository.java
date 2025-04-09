package ru.practicum.explore_with_me.stat.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.practicum.explore_with_me.stat.server.dto.StatView;
import ru.practicum.explore_with_me.stat.server.model.Hit;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface HitRepository extends JpaRepository<Hit, Long> {

    @Query(
            value = "SELECT h.app, h.uri, COUNT(DISTINCT h.ip) hits "
                    + "FROM hits as h "
                    + "WHERE h.created_at >= :start AND h.created_at <= :end "
                    + "GROUP BY h.app, h.uri "
                    + "ORDER BY hits DESC",
            nativeQuery = true
    )
    List<StatView> getStatsByDatesUnique(LocalDateTime start, LocalDateTime end);

    @Query(
            value = "SELECT h.app, h.uri, COUNT(h.ip) hits "
                    + "FROM hits as h "
                    + "WHERE h.created_at >= :start AND h.created_at <= :end "
                    + "GROUP BY h.app, h.uri "
                    + "ORDER BY hits DESC",
            nativeQuery = true
    )
    List<StatView> getStatsByDates(LocalDateTime start, LocalDateTime end);

    @Query(
            value = "SELECT h.app, h.uri, COUNT(DISTINCT h.ip) hits "
                    + "FROM hits as h "
                    + "WHERE h.created_at >= :start AND h.created_at <= :end AND h.uri IN (:uris) "
                    + "GROUP BY h.app, h.uri "
                    + "ORDER BY hits DESC",
            nativeQuery = true
    )
    List<StatView> getStatsByDatesAndUriUnique(LocalDateTime start, LocalDateTime end, List<String> uris);

    @Query(
            value = "SELECT h.app, h.uri, COUNT(h.ip) hits "
                    + "FROM hits as h "
                    + "WHERE h.created_at >= :start AND h.created_at <= :end AND h.uri IN (:uris) "
                    + "GROUP BY h.app, h.uri "
                    + "ORDER BY hits DESC",
            nativeQuery = true
    )
    List<StatView> getStatsByDatesAndUri(LocalDateTime start, LocalDateTime end, List<String> uris);

}
