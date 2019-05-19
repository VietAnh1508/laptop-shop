package com.laptopshop.service;

import com.laptopshop.entity.Category;
import com.laptopshop.exception.BadRequestException;
import com.laptopshop.exception.ResourceNotFoundException;

import java.util.List;

public interface CategoryService {

    List<Category> getAll();

    Category getById(Integer id) throws ResourceNotFoundException;

    Category create(Category newCategory) throws BadRequestException, ResourceNotFoundException;

    Category update(Integer id, Category category) throws BadRequestException, ResourceNotFoundException;

    void deleteById(Integer id);

}
