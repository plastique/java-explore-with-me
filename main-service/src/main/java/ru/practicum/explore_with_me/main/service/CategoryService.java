package ru.practicum.explore_with_me.main.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.explore_with_me.main.dto.admin.category.AdminCategoryDto;
import ru.practicum.explore_with_me.main.dto.admin.category.AdminCreateCategoryDto;
import ru.practicum.explore_with_me.main.dto.admin.category.AdminUpdateCategoryDto;
import ru.practicum.explore_with_me.main.dto.category.CategoryDto;
import ru.practicum.explore_with_me.main.exception.LogicErrorException;
import ru.practicum.explore_with_me.main.exception.UniqueException;
import ru.practicum.explore_with_me.main.exception.DataErrorException;
import ru.practicum.explore_with_me.main.exception.NotFoundException;
import ru.practicum.explore_with_me.main.mapper.CategoryMapper;
import ru.practicum.explore_with_me.main.model.Category;
import ru.practicum.explore_with_me.main.repository.CategoryRepository;
import ru.practicum.explore_with_me.main.repository.EventRepository;
import ru.practicum.explore_with_me.main.service.contracts.CategoryServiceInterface;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService implements CategoryServiceInterface {

    private final CategoryRepository categoryRepository;
    private final EventRepository eventRepository;

    @Transactional
    public AdminCategoryDto create(AdminCreateCategoryDto dto) {

        if (categoryRepository.existsByName(dto.getName())) {
            throw new UniqueException("Category with name " + dto.getName() + " already exists");
        }

        Category category = CategoryMapper.toEntity(dto);

        try {
            category = categoryRepository.save(category);
        } catch (RuntimeException e) {
            throw new DataErrorException("DB error: " + e.getMessage());
        }

        return CategoryMapper.toAdminDto(category);
    }

    @Override
    @Transactional
    public void delete(Long id) {

        if (!categoryRepository.existsById(id)) {
            throw new NotFoundException("Category with id " + id + " does not exist");
        }

        if (eventRepository.existsByCategory_Id(id)) {
            throw new LogicErrorException("Category with id " + id + " has events");
        }

        try {
            categoryRepository.deleteById(id);
        } catch (RuntimeException e) {
            throw new DataErrorException("DB error: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public AdminCategoryDto update(Long id, AdminUpdateCategoryDto dto) {

        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Category with id " + id + " does not exist")
        );

        if (categoryRepository.existsByNameAndIdNot(dto.getName(), id)) {
            throw new UniqueException("Category with name " + dto.getName() + " already exists");
        }

        category.setName(dto.getName());

        try {
            category = categoryRepository.save(category);
        } catch (RuntimeException e) {
            throw new DataErrorException("DB error: " + e.getMessage());
        }

        return CategoryMapper.toAdminDto(category);
    }

    @Override
    public CategoryDto getById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Category with id " + id + " does not exist")
        );

        return CategoryMapper.toDto(category);
    }

    @Override
    public List<CategoryDto> getList(int from, int size) {
        Pageable pageable = PageRequest.of(from / size, size);

        return categoryRepository.findAll(pageable).stream().map(CategoryMapper::toDto).toList();
    }

}
