package ru.practicum.explore_with_me.main.dto.user.event;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateCommentDto {

    @NotBlank
    @Size(min = 5, max = 2000)
    private String text;
}
