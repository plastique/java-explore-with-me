package ru.practicum.explore_with_me.main.service.contracts;

import ru.practicum.explore_with_me.main.dto.admin.user.AdminCreateUserDto;
import ru.practicum.explore_with_me.main.dto.admin.user.AdminUserDto;

import java.util.List;

public interface UserServiceInterface {

    List<AdminUserDto> getList(int from, int size, List<Long> ids);

    AdminUserDto create(AdminCreateUserDto dto);

    void delete(Long id);
}
