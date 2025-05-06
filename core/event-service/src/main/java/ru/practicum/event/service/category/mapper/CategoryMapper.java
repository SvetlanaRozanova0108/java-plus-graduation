package ru.practicum.event.service.category.mapper;

import lombok.experimental.UtilityClass;
import api.dto.category.CategoryDto;
import main.java.api.dto.category.NewCategoryDto;
import ru.practicum.event.service.category.model.Category;

@UtilityClass
public class CategoryMapper {

    public Category toCategory(NewCategoryDto newCategoryDto) {
        return Category.builder()
                .name(newCategoryDto.getName())
                .build();
    }

    public CategoryDto toCategoryDto(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
