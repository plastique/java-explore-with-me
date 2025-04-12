package ru.practicum.explore_with_me.main.exception;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ApiErrorMessage {
    private List<String> errors;

    private String message;

    private String reason;

    private String status;

    private LocalDateTime timestamp;
}
