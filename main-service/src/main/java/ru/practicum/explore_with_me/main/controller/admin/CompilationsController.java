package ru.practicum.explore_with_me.main.controller.admin;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.explore_with_me.main.dto.admin.category.CreateCategoryDto;
import ru.practicum.explore_with_me.main.dto.admin.category.UpdateCategoryDto;
import ru.practicum.explore_with_me.main.dto.admin.compilation.CompilationDto;

@RestController
@RequestMapping("/admin/compilations")
public class CompilationsController {

    @PostMapping
    public CompilationDto create(
            @RequestBody @Valid CreateCategoryDto dto
    ) {
        return null;
    }

    @DeleteMapping("/{compId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @Positive int compId) {
    }

    @PatchMapping("/{compId}")
    public CompilationDto update(
            @PathVariable @Positive int compId,
            @RequestBody @Valid UpdateCategoryDto dto
    ) {
        return null;
    }

}
