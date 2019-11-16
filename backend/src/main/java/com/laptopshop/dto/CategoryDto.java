package com.laptopshop.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.laptopshop.entity.Category;
import com.laptopshop.entity.Product;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CategoryDto {

    private Integer id;

    @NotBlank(message = "Category name is required")
    private String name;

    private Category parentCategory;

    private Set<Category> subCategories = new HashSet<>();

    @JsonIgnore
    private List<Product> productList;

    public CategoryDto() {
    }

    public CategoryDto(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public CategoryDto(Integer id, String name, Category parentCategory) {
        this.id = id;
        this.name = name;
        this.parentCategory = parentCategory;
    }

    public Integer getId() {
        return id;
    }

    @ApiModelProperty(hidden = true)
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    public Category getParentCategory() {
        return parentCategory;
    }

    @JsonProperty
    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    public Set<Category> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(Set<Category> subCategories) {
        this.subCategories = subCategories;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

}
