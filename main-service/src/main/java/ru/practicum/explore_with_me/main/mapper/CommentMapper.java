package ru.practicum.explore_with_me.main.mapper;

import lombok.experimental.UtilityClass;
import ru.practicum.explore_with_me.main.dto.comment.CommentDto;
import ru.practicum.explore_with_me.main.model.Comment;

@UtilityClass
public class CommentMapper {

    public static CommentDto toDto(final Comment comment) {
        return CommentDto
                .builder()
                .id(comment.getId())
                .text(comment.getText())
                .author(
                        new CommentDto.AuthorDto(
                                comment.getUser().getId(),
                                comment.getUser().getName()
                        )
                )
                .created(comment.getCreated())
                .updated(comment.getUpdated())
                .build();
    }

}
