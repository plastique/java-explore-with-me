package ru.practicum.explore_with_me.main.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.practicum.explore_with_me.main.model.Event;
import ru.practicum.explore_with_me.main.model.EventState;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Long>, JpaSpecificationExecutor<Event> {

    Optional<Event> findByIdAndState(Long id, EventState eventState);

    List<Event> findAllByUser_Id(Long id, Pageable pageable);

    Optional<Event> findByIdAndUser_Id(Long userId, Long id);

    boolean existsByIdAndUser_Id(Long id, Long userId);

}
