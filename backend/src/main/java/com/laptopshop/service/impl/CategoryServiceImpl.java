package com.laptopshop.service.impl;

import com.laptopshop.entity.Category;
import com.laptopshop.exception.BadRequestException;
import com.laptopshop.exception.ResourceNotFoundException;
import com.laptopshop.repository.CategoryRepository;
import com.laptopshop.service.CategoryService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getById(Integer id) throws ResourceNotFoundException {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found for this id: " + id));
    }

    @Override
    public Category create(Category newCategory) throws BadRequestException, ResourceNotFoundException {
        if (newCategory.getParentCategory() != null) {
            Integer parentCategoryId = newCategory.getParentCategory().getId();

            Category parentCategory = categoryRepository.findById(parentCategoryId)
                    .orElseThrow(() -> new ResourceNotFoundException("Parent category not found for this id: " + parentCategoryId));

            newCategory.setParentCategory(parentCategory);
        }

        if (StringUtils.isEmpty(newCategory.getName())) {
            throw new BadRequestException("Category name is required");
        }

        return categoryRepository.save(newCategory);
    }

    @Override
    public Category update(Integer id, Category category) throws BadRequestException, ResourceNotFoundException {
        Category oldCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found for this id: " + category.getId()));

        if (StringUtils.isEmpty(category.getName())) {
            throw new BadRequestException("Category name is required");
        }
        oldCategory.setName(category.getName());

        if (CollectionUtils.isNotEmpty(category.getChildCategories())) {
            oldCategory.setChildCategories(category.getChildCategories());
        }

        return categoryRepository.save(oldCategory);
    }

    @Override
    public void deleteById(Integer id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            categoryRepository.deleteById(id);
        }
    }

}
