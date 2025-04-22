package ru.practicum.explore_with_me.main.dto.contracts;

import java.time.LocalDateTime;

public interface UpdateEventDto {

    String getAnnotation();

    String getDescription();

    String getTitle();

    Long getCategory();

    Boolean getPaid();

    Boolean getRequestModeration();

    Integer getParticipantLimit();

    LocationDto getLocation();

    LocalDateTime getEventDate();

    <T extends Enum<T>> Enum<T> getStateAction();

    record LocationDto(float lat, float lon) {
    }

}
