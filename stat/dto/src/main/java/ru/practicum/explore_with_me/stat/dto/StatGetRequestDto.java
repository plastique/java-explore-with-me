package ru.practicum.explore_with_me.stat.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import ru.practicum.explore_with_me.stat.constant.Constants;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class StatGetRequestDto {

    @NotNull
    @DateTimeFormat(pattern = Constants.DATE_TIME_FORMAT)
    private LocalDateTime start;

    @NotNull
    @DateTimeFormat(pattern = Constants.DATE_TIME_FORMAT)
    private LocalDateTime end;

    @NotNull
    private List<String> uris;

    private boolean unique;
}
