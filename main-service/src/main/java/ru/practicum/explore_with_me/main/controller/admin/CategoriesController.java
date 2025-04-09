package ru.practicum.explore_with_me.main.controller.admin;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
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
import ru.practicum.explore_with_me.main.dto.admin.category.CategoryDto;
import ru.practicum.explore_with_me.main.dto.admin.category.CreateCategoryDto;
import ru.practicum.explore_with_me.main.dto.admin.category.UpdateCategoryDto;

@RestController
@RequestMapping("/admin/categories")
@Validated
public class CategoriesController {

    @PostMapping
    public CategoryDto create(
            @RequestBody @Valid CreateCategoryDto dto
    ) {
        return null;
    }

    @DeleteMapping("/{catId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @Positive int catId) {
    }

    @PatchMapping("/{catId}")
    public CategoryDto update(
            @PathVariable @Positive int catId,
            @RequestBody @Valid UpdateCategoryDto dto
    ) {
        return null;
    }

}
