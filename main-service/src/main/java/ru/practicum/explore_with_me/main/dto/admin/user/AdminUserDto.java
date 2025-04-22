package ru.practicum.explore_with_me.main.dto.admin.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdminUserDto {

    private Long id;
    private String name;
    private String email;

}
