package ru.practicum.explore_with_me.main.service.contracts;

import ru.practicum.explore_with_me.main.dto.comment.CommentDto;
import ru.practicum.explore_with_me.main.dto.user.event.UserCreateCommentDto;
import ru.practicum.explore_with_me.main.dto.user.event.UserUpdateCommentDto;

import java.util.List;

public interface CommentServiceInterface {

    List<CommentDto> getListByEvent(Long eventId, int from, int size);

    CommentDto create(Long eventId, Long authorId, UserCreateCommentDto dto);

    CommentDto update(Long eventId, Long authorId, UserUpdateCommentDto dto);

    void deleteByAuthor(Long eventId, Long authorId, Long id);

    void deleteByAdmin(Long id);

}
