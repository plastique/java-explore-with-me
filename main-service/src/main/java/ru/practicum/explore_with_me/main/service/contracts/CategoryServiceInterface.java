package ru.practicum.explore_with_me.main.service.contracts;

import ru.practicum.explore_with_me.main.dto.admin.category.AdminCategoryDto;
import ru.practicum.explore_with_me.main.dto.admin.category.AdminCreateCategoryDto;
import ru.practicum.explore_with_me.main.dto.admin.category.AdminUpdateCategoryDto;
import ru.practicum.explore_with_me.main.dto.category.CategoryDto;

import java.util.List;

public interface CategoryServiceInterface {

    AdminCategoryDto create(AdminCreateCategoryDto dto);

    void delete(Long id);

    AdminCategoryDto update(Long id, AdminUpdateCategoryDto dto);

    CategoryDto getById(Long id);

    List<CategoryDto> getList(int from, int size);

}
