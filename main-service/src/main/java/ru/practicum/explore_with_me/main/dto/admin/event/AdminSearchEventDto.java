package ru.practicum.explore_with_me.main.dto.admin.event;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Data;
import ru.practicum.explore_with_me.main.model.EventState;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
public class AdminSearchEventDto {

    private Set<Long> users;

    private Set<EventState> states;

    private Set<Long> categories;

    private LocalDateTime rangeStart;

    private LocalDateTime rangeEnd;

    @PositiveOrZero
    private int from;

    @Positive
    private int size;

}
