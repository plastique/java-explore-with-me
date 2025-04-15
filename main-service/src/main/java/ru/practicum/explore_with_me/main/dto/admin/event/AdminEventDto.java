package ru.practicum.explore_with_me.main.dto.admin.event;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class AdminEventDto {

    private String annotation;

    private CategoryDto category;

    private int confirmedRequests;

    private LocalDateTime createdOn;

    private String description;

    private LocalDateTime eventDate;

    private InitiatorDto initiator;

    private LocationDto location;

    private boolean paid;

    private int participantLimit;

    private LocalDateTime publishedOn;

    private boolean requestModeration;

    private String state;

    private String title;

    private int views;

    public record CategoryDto(Long id, String name) {}

    public record InitiatorDto(Long id, String name) {}

    public record LocationDto(float lat, float lon) {}

}
