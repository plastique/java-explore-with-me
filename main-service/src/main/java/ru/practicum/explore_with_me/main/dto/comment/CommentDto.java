package ru.practicum.explore_with_me.main.dto.comment;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import ru.practicum.explore_with_me.main.constant.Constant;

import java.time.LocalDateTime;

@Data
@Builder
public class CommentDto {

    private Long id;
    private String text;
    private AuthorDto author;

    @JsonFormat(pattern = Constant.DATE_TIME_FORMAT, shape = JsonFormat.Shape.STRING)
    private LocalDateTime created;

    @JsonFormat(pattern = Constant.DATE_TIME_FORMAT, shape = JsonFormat.Shape.STRING)
    private LocalDateTime updated;

    public record AuthorDto(Long id, String name) {}

}
