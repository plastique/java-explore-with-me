package ru.practicum.explore_with_me.main.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ApiErrorMessage {

    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private List<String> errors;

    private String message;

    private String reason;

    private String status;

    @DateTimeFormat(pattern = DATE_TIME_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_TIME_FORMAT)
    private LocalDateTime timestamp;

}
