package ru.practicum.explore_with_me.main.controller;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.explore_with_me.main.constant.Constant;
import ru.practicum.explore_with_me.main.dto.compilation.CompilationDto;
import ru.practicum.explore_with_me.main.dto.compilation.CompilationListDto;

import java.util.List;

@RestController
@RequestMapping("/compilations")
@Validated
public class CompilationController {

    @GetMapping
    public List<CompilationListDto> getList(
            @RequestParam(value = "pinned", defaultValue = "false") boolean pinned,
            @RequestParam(value = "from", defaultValue = Constant.INT_MIN_STRING) @PositiveOrZero int from,
            @RequestParam(value = "size", defaultValue = "10") @Positive int size
    ) {
        return List.of();
    }

    @GetMapping("/{compId}")
    public CompilationDto getById(@PathVariable Integer compId) {
        return null;
    }

}
