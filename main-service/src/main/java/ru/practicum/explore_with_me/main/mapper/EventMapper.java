package ru.practicum.explore_with_me.main.mapper;

import lombok.experimental.UtilityClass;
import ru.practicum.explore_with_me.main.dto.event.EventDto;
import ru.practicum.explore_with_me.main.dto.event.EventShortDto;
import ru.practicum.explore_with_me.main.dto.user.event.UserCreateEventDto;
import ru.practicum.explore_with_me.main.model.Category;
import ru.practicum.explore_with_me.main.model.Event;
import ru.practicum.explore_with_me.main.model.Location;
import ru.practicum.explore_with_me.main.model.User;

@UtilityClass
public class EventMapper {

    public static EventDto toDto(final Event event, int views) {
        return EventDto
                .builder()
                .id(event.getId())
                .annotation(event.getAnnotation())
                .category(new EventDto.CategoryDto(
                        event.getCategory().getId(),
                        event.getCategory().getName()
                ))
                .confirmedRequests(event.getConfirmedRequests())
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

    public static EventShortDto toShortDto(final Event event, int views) {
        return EventShortDto
                .builder()
                .id(event.getId())
                .annotation(event.getAnnotation())
                .category(new EventShortDto.CategoryDto(
                        event.getCategory().getId(),
                        event.getCategory().getName()
                ))
                .confirmedRequests(event.getConfirmedRequests())
                .eventDate(event.getEventDate())
                .initiator(new EventShortDto.InitiatorDto(
                        event.getUser().getId(),
                        event.getUser().getName()
                ))
                .paid(event.isPaid())
                .title(event.getTitle())
                .views(views)
                .build();
    }

    public static Event toEntity(final UserCreateEventDto dto, User user, Category category) {
        Event event = new Event();

        Location location = new Location();
        location.setLat(dto.getLocation().lat());
        location.setLon(dto.getLocation().lon());

        event.setCategory(category);
        event.setUser(user);
        event.setTitle(dto.getTitle());
        event.setAnnotation(dto.getAnnotation());
        event.setDescription(dto.getDescription());
        event.setEventDate(dto.getEventDate());
        event.setLocation(location);
        event.setPaid(dto.isPaid());
        event.setParticipantLimit(dto.getParticipantLimit());
        event.setRequestModeration(dto.isRequestModeration());

        return event;
    }
}
