package ru.practicum.explore_with_me.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.practicum.explore_with_me.main.model.Request;
import ru.practicum.explore_with_me.main.model.RequestState;

import java.util.Map;
import java.util.Set;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {

    int countByEvent_IdAndState(Long id, RequestState requestState);

    @Query(
            value = "SELECT r.event_id, COUNT(*) "
                    + "FROM event_requests r "
                    + "WHERE r.event_id IN (:ids) AND r.state = :requestState "
                    + "GROUP BY r.event_id ",
            nativeQuery = true
    )
    Map<Long, Integer> countByEvent_IdInAndState(Set<Long> ids, RequestState requestState);

}
