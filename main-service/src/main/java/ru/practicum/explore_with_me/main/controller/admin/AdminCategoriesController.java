package ru.practicum.explore_with_me.main.controller.admin;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.explore_with_me.main.dto.admin.category.AdminCategoryDto;
import ru.practicum.explore_with_me.main.dto.admin.category.AdminCreateCategoryDto;
import ru.practicum.explore_with_me.main.dto.admin.category.AdminUpdateCategoryDto;
import ru.practicum.explore_with_me.main.service.contracts.CategoryServiceInterface;

@RestController
@RequestMapping("/admin/categories")
@Validated
@RequiredArgsConstructor
public class AdminCategoriesController {

    private final CategoryServiceInterface categoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AdminCategoryDto create(
            @RequestBody @Valid AdminCreateCategoryDto dto
    ) {
        return categoryService.create(dto);
    }

    @DeleteMapping("/{catId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @Positive Long catId) {
        categoryService.delete(catId);
    }

    @PatchMapping("/{catId}")
    public AdminCategoryDto update(
            @PathVariable @Positive Long catId,
            @RequestBody @Valid AdminUpdateCategoryDto dto
    ) {
        return categoryService.update(catId, dto);
    }

}
