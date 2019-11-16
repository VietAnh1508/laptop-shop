package com.laptopshop.service;

import com.laptopshop.dto.CategoryDto;
import com.laptopshop.exception.BadRequestException;
import com.laptopshop.exception.ResourceNotFoundException;

import java.util.List;

public interface CategoryService {

    List<CategoryDto> getAll();

    List<CategoryDto> getAllParentCategories();

    List<CategoryDto> getChildCategoriesByParent(Integer id);

    CategoryDto getById(Integer id) throws ResourceNotFoundException;

    CategoryDto create(CategoryDto newCategory) throws BadRequestException;

    CategoryDto update(Integer id, CategoryDto category) throws ResourceNotFoundException;

    void deleteById(Integer id);

}
