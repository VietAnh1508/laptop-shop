package com.laptopshop.repository;

import com.laptopshop.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query("select new Category(id, name) from Category where parentCategory = null")
    List<Category> getParentCategories();

    List<Category> getCategoriesByParentCategoryId(Integer id);

}
