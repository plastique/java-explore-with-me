package ru.practicum.explore_with_me.main.mapper;

import lombok.experimental.UtilityClass;
import ru.practicum.explore_with_me.main.dto.event.EventDto;
import ru.practicum.explore_with_me.main.model.Event;

@UtilityClass
public class EventMapper {

    public static EventDto toDto(final Event event, int confirmedRequests, int views) {
        return EventDto
                .builder()
                .annotation(event.getAnnotation())
                .category(new EventDto.CategoryDto(
                        event.getCategory().getId(),
                        event.getCategory().getName()
                ))
                .confirmedRequests(confirmedRequests)
                .createdOn(event.getCreated())
                .description(event.getDescription())
                .eventDate(event.getEventDate())
                .initiator(new EventDto.InitiatorDto(
                        event.getUser().getId(),
                        event.getUser().getName()
                ))
                .location(new EventDto.LocationDto(
                        event.getLocation().getLat(),
                        event.getLocation().getLon()
                ))
                .paid(event.isPaid())
                .participantLimit(event.getParticipantLimit())
                .publishedOn(event.getPublished())
                .requestModeration(event.isRequestModeration())
                .state(event.getState().name())
                .title(event.getTitle())
                .views(views)
                .build();
    }
}
