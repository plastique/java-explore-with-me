package ru.practicum.explore_with_me.main.mapper;

import lombok.experimental.UtilityClass;
import ru.practicum.explore_with_me.main.dto.admin.category.AdminCategoryDto;
import ru.practicum.explore_with_me.main.dto.admin.category.AdminCreateCategoryDto;
import ru.practicum.explore_with_me.main.dto.category.CategoryDto;
import ru.practicum.explore_with_me.main.model.Category;

@UtilityClass
public class CategoryMapper {

    public static AdminCategoryDto toAdminDto(final Category category) {
        return AdminCategoryDto
                .builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    public static Category toEntity(final AdminCreateCategoryDto createCategoryDto) {
        return new Category(
                null,
                createCategoryDto.getName()
        );
    }

    public static CategoryDto toDto(final Category category) {
        return CategoryDto
                .builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
