package com.laptopshop.repository;

import com.laptopshop.dto.CategoryDto;
import com.laptopshop.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query("select new com.laptopshop.dto.CategoryDto(id, name) from Category where parentCategory = null")
    List<CategoryDto> getParentCategories();

    @Query("select new com.laptopshop.dto.CategoryDto(id, name) from Category where parentCategory.id = ?1")
    List<CategoryDto> getCategoriesByParentCategoryId(Integer id);

}
