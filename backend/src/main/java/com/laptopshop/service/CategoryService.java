package com.laptopshop.service;

import com.laptopshop.entity.Category;
import com.laptopshop.exception.ResourceNotFoundException;

import java.util.List;

public interface CategoryService {

    List<Category> getAll();

    List<Category> getAllParentCategories();

    List<Category> getChildCategoriesByParent(Integer id);

    Category getById(Integer id) throws ResourceNotFoundException;

    Category create(Category newCategory) throws ResourceNotFoundException;

    Category update(Integer id, Category category) throws ResourceNotFoundException;

    void deleteById(Integer id);

}
