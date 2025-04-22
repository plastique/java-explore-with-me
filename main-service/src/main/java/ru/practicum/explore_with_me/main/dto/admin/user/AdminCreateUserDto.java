package ru.practicum.explore_with_me.main.dto.admin.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AdminCreateUserDto {

    @NotBlank
    @Size(min = 2, max = 250)
    private String name;

    @Email
    @NotBlank
    @Size(min = 6, max = 254)
    private String email;

}
