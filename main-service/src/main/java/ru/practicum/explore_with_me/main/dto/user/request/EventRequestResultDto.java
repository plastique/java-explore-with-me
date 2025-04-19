package ru.practicum.explore_with_me.main.dto.user.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventRequestResultDto {

    private List<EventRequestDto> confirmedRequests;
    private List<EventRequestDto> rejectedRequests;

}
