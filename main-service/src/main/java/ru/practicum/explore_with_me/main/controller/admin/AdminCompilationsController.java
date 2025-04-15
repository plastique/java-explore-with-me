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
import ru.practicum.explore_with_me.main.dto.admin.category.AdminCreateCategoryDto;
import ru.practicum.explore_with_me.main.dto.admin.category.AdminUpdateCategoryDto;
import ru.practicum.explore_with_me.main.dto.admin.compilation.AdminCompilationDto;

@RestController
@RequestMapping("/admin/compilations")
@Validated
public class AdminCompilationsController {

    @PostMapping
    public AdminCompilationDto create(
            @RequestBody @Valid AdminCreateCategoryDto dto
    ) {
        return null;
    }

    @DeleteMapping("/{compId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @Positive int compId) {
    }

    @PatchMapping("/{compId}")
    public AdminCompilationDto update(
            @PathVariable @Positive int compId,
            @RequestBody @Valid AdminUpdateCategoryDto dto
    ) {
        return null;
    }

}
