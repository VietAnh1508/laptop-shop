package com.laptopshop.controller;

import com.laptopshop.entity.Category;
import com.laptopshop.exception.ResourceNotFoundException;
import com.laptopshop.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "Category")
@RestController
@RequestMapping("/categories")
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Get all categories")
    public ResponseEntity getAllCategories() {
        List<Category> categories = categoryService.getAll();
        return ResponseEntity.ok(categories);
    }

    @GetMapping(value = "/parent", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Get all parent categories")
    public ResponseEntity getAllParentCategories() {
        List<Category> categories = categoryService.getAllParentCategories();
        return ResponseEntity.ok(categories);
    }

    @GetMapping(value = "/children", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Get child categories by parent")
    public ResponseEntity getCategoriesByParent(@RequestParam Integer parentId) {
        List<Category> categories = categoryService.getChildCategoriesByParent(parentId);
        return ResponseEntity.ok(categories);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Get category by id")
    public ResponseEntity getById(@PathVariable Integer id) throws ResourceNotFoundException {
        Category category = categoryService.getById(id);
        return ResponseEntity.ok(category);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Add a new category")
    public ResponseEntity addNewCategory(@Valid @RequestBody Category newCategory)
            throws ResourceNotFoundException {
        Category category = categoryService.create(newCategory);
        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }

    @PutMapping(
            value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Update category")
    public ResponseEntity updateCategory(@PathVariable Integer id, @Valid @RequestBody Category category)
            throws ResourceNotFoundException {
        Category editedCategory = categoryService.update(id, category);
        return ResponseEntity.ok(editedCategory);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Delete category by id")
    public ResponseEntity deleteCategory(@PathVariable Integer id) {
        categoryService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
