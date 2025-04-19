package ru.practicum.explore_with_me.main.dto.user.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.explore_with_me.main.constant.Constant;
import ru.practicum.explore_with_me.main.model.EventRequestState;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventRequestDto {

    private Long id;
    private Long event;
    private Long requestor;
    private EventRequestState status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constant.DATE_TIME_FORMAT)
    private LocalDateTime created;

}
