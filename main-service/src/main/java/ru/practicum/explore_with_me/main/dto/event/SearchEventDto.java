package ru.practicum.explore_with_me.main.dto.event;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Data;
import ru.practicum.explore_with_me.main.constant.Sort;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
public class SearchEventDto {
    private String text;

    private Set<Long> categories;

    private boolean paid;

    private LocalDateTime rangeStart;

    private LocalDateTime rangeEnd;

    private boolean onlyAvailable;

    private Sort sort;

    @PositiveOrZero
    private int from;

    @Positive
    private int size;
}
