package com.laptopshop.service.impl;

import com.laptopshop.dto.CategoryDto;
import com.laptopshop.entity.Category;
import com.laptopshop.exception.BadRequestException;
import com.laptopshop.exception.ResourceNotFoundException;
import com.laptopshop.repository.CategoryRepository;
import com.laptopshop.service.CategoryService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<CategoryDto> getAll() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(category -> modelMapper.map(category, CategoryDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<CategoryDto> getAllParentCategories() {
        return categoryRepository.getParentCategories();
    }

    @Override
    public List<CategoryDto> getChildCategoriesByParent(Integer id) {
        return categoryRepository.getCategoriesByParentCategoryId(id);
    }

    @Override
    public CategoryDto getById(Integer id) throws ResourceNotFoundException {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Category.class, id));
        return modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public CategoryDto create(CategoryDto newCategory) throws BadRequestException {
        if (newCategory.getParentCategory() != null) {
            Integer parentCategoryId = newCategory.getParentCategory().getId();

            Category parentCategory = categoryRepository.findById(parentCategoryId)
                    .orElseThrow(() -> new BadRequestException("Invalid parent category"));

            newCategory.setParentCategory(parentCategory);
        }

        Category category = categoryRepository.save(modelMapper.map(newCategory, Category.class));
        return modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public CategoryDto update(Integer id, CategoryDto category) throws ResourceNotFoundException {
        Category oldCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Category.class, id));

        String categoryName = category.getName();
        if (StringUtils.isNotEmpty(categoryName)) {
            oldCategory.setName(categoryName);
        }

        Set<Category> subCategories = category.getSubCategories();
        if (CollectionUtils.isNotEmpty(subCategories)) {
            oldCategory.setChildCategories(subCategories);
        }

        Category updatedCategory = categoryRepository.save(oldCategory);
        return modelMapper.map(updatedCategory, CategoryDto.class);
    }

    @Override
    public void deleteById(Integer id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            categoryRepository.deleteById(id);
        }
    }

}
