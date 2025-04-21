package ru.practicum.explore_with_me.main.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.practicum.explore_with_me.main.dto.admin.user.AdminCreateUserDto;
import ru.practicum.explore_with_me.main.dto.admin.user.AdminUserDto;
import ru.practicum.explore_with_me.main.exception.DataErrorException;
import ru.practicum.explore_with_me.main.exception.NotFoundException;
import ru.practicum.explore_with_me.main.exception.UniqueException;
import ru.practicum.explore_with_me.main.mapper.UserMapper;
import ru.practicum.explore_with_me.main.model.User;
import ru.practicum.explore_with_me.main.repository.UserRepository;
import ru.practicum.explore_with_me.main.service.contracts.UserServiceInterface;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserServiceInterface {
    private final UserRepository userRepository;

    @Override
    public List<AdminUserDto> getList(int from, int size, List<Long> ids) {
        Pageable pageable = PageRequest.of(from / size, size);

        return ids == null || ids.isEmpty()
            ? userRepository.findAll(pageable).stream().map(UserMapper::toAdminDto).toList()
            : userRepository.findAllByIdIn(ids, pageable);
    }

    @Override
    public AdminUserDto create(AdminCreateUserDto dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new UniqueException("User with email " + dto.getEmail() + " already exists");
        }

        User user = UserMapper.toEntity(dto);

        try {
            user = userRepository.save(user);
        } catch (DataAccessException e) {
            throw new DataErrorException("DB error: " + e.getMessage());
        }

        return UserMapper.toAdminDto(user);
    }

    @Override
    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new NotFoundException("User with id " + id + " does not exist");
        }

        try {
            userRepository.deleteById(id);
        } catch (DataAccessException e) {
            throw new DataErrorException("DB error: " + e.getMessage());
        }
    }

}
