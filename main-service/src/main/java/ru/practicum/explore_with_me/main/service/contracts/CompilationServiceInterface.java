package ru.practicum.explore_with_me.main.service.contracts;

import ru.practicum.explore_with_me.main.dto.admin.compilation.AdminCreateCompilationDto;
import ru.practicum.explore_with_me.main.dto.admin.compilation.AdminUpdateCompilationDto;
import ru.practicum.explore_with_me.main.dto.compilation.CompilationDto;

import java.util.List;

public interface CompilationServiceInterface {

    List<CompilationDto> getList(boolean pinned, int from, int size);

    CompilationDto getById(Long id);

    CompilationDto createByAdmin(AdminCreateCompilationDto dto);

    void deleteByAdmin(Long id);

    CompilationDto updateByAdmin(Long id, AdminUpdateCompilationDto dto);
}
