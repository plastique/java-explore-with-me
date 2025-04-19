package ru.practicum.explore_with_me.main.mapper;

import lombok.experimental.UtilityClass;
import ru.practicum.explore_with_me.main.dto.admin.user.AdminCreateUserDto;
import ru.practicum.explore_with_me.main.dto.admin.user.AdminUserDto;
import ru.practicum.explore_with_me.main.model.User;

@UtilityClass
public class UserMapper {

    public static AdminUserDto toAdminDto(final User user) {
        return AdminUserDto
                .builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

    public static User toEntity(final AdminCreateUserDto createUserDto) {
        return new User(
                null,
                createUserDto.getName(),
                createUserDto.getEmail()
        );
    }
}
