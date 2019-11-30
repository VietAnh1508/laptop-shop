package com.laptopshop.controller;

import com.laptopshop.dto.CategoryDto;
import com.laptopshop.exception.BadRequestException;
import com.laptopshop.exception.ResourceNotFoundException;
import com.laptopshop.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Get all categories")
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        List<CategoryDto> categories = categoryService.getAll();
        return ResponseEntity.ok(categories);
    }

    @GetMapping(value = "/parent", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Get all parent categories")
    public ResponseEntity<List<CategoryDto>> getAllParentCategories() {
        List<CategoryDto> categories = categoryService.getAllParentCategories();
        return ResponseEntity.ok(categories);
    }

    @GetMapping(value = "/children", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Get child categories by parent")
    public ResponseEntity<List<CategoryDto>> getCategoriesByParent(@RequestParam Integer parentId) {
        List<CategoryDto> categories = categoryService.getChildCategoriesByParent(parentId);
        return ResponseEntity.ok(categories);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Get category by id")
    public ResponseEntity<CategoryDto> getById(@PathVariable Integer id) throws ResourceNotFoundException {
        CategoryDto category = categoryService.getById(id);
        return ResponseEntity.ok(category);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Add a new category")
    public ResponseEntity<CategoryDto> addNewCategory(@Valid @RequestBody CategoryDto newCategory)
            throws BadRequestException {
        CategoryDto category = categoryService.create(newCategory);
        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }

    @PutMapping(
            value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Update category")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable Integer id, @RequestBody CategoryDto category)
            throws ResourceNotFoundException {
        CategoryDto editedCategory = categoryService.update(id, category);
        return ResponseEntity.ok(editedCategory);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Delete category by id")
    public ResponseEntity<Void> deleteCategory(@PathVariable Integer id) {
        categoryService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
