package ru.practicum.explore_with_me.main.dto.user.event;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ru.practicum.explore_with_me.main.model.EventRequestState;

import java.util.List;

@Data
public class UpdateEventRequestsDto {

    @NotNull
    @NotEmpty
    private List<Long> requestIds;

    @NotNull
    private EventRequestState status;

}
