package ru.practicum.explore_with_me.main.dto.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import ru.practicum.explore_with_me.main.constant.Constant;

import java.time.LocalDateTime;

@Data
@Builder
public class EventShortDto {

    private Long id;

    private String annotation;

    private CategoryDto category;

    private int confirmedRequests;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constant.DATE_TIME_FORMAT)
    private LocalDateTime eventDate;

    private InitiatorDto initiator;

    private boolean paid;

    private String title;

    private int views;

    public record CategoryDto(Long id, String name) {}

    public record InitiatorDto(Long id, String name) {}

}
