package ru.practicum.explore_with_me.main.dto.category;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryDto {

    private Long id;
    private String name;

}
