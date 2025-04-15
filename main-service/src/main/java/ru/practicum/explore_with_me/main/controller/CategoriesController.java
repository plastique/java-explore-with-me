package ru.practicum.explore_with_me.main.controller;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.explore_with_me.main.constant.Constant;
import ru.practicum.explore_with_me.main.dto.category.CategoryDto;
import ru.practicum.explore_with_me.main.service.contracts.CategoryServiceInterface;

import java.util.List;

@RestController
@RequestMapping("/categories")
@Validated
@RequiredArgsConstructor
public class CategoriesController {

    private final CategoryServiceInterface categoryService;

    @GetMapping
    public List<CategoryDto> getList(
            @RequestParam(value = "from", defaultValue = Constant.INT_MIN_STRING) @PositiveOrZero int from,
            @RequestParam(value = "size", defaultValue = "10") @Positive int size
    ) {
        return categoryService.getList(from, size);
    }

    @GetMapping("/{catId}")
    public CategoryDto getById(@PathVariable Long catId) {
        return categoryService.getById(catId);
    }

}
