package ru.practicum.explore_with_me.main.controller.admin;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.explore_with_me.main.constant.Constant;
import ru.practicum.explore_with_me.main.dto.admin.user.CreateUserDto;
import ru.practicum.explore_with_me.main.dto.admin.user.UserDto;
import ru.practicum.explore_with_me.main.dto.admin.user.UserListDto;

import java.util.List;

@RestController
@RequestMapping("/admin/users")
@Validated
public class UsersController {

    @GetMapping
    public List<UserListDto> getList(
            @RequestParam(value = "ids", required = false) List<Integer> ids,
            @RequestParam(value = "from", defaultValue = Constant.INT_MIN_STRING) @PositiveOrZero int from,
            @RequestParam(value = "size", defaultValue = "10") @Positive int size
    ) {
        return List.of();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto create(
            @RequestBody @Valid CreateUserDto dto
    ) {
        return null;
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @Positive int userId) {
    }

}
