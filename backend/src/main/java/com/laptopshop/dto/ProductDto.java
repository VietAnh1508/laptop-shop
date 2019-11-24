package com.laptopshop.dto;

import io.swagger.annotations.ApiModelProperty;

public class ProductDto {

    private Long id;

    private String name;

    private Double price;

    private Byte discount;

    private String imageUrl;

    private String description;

    private Integer categoryId;

    private Integer brandId;

    public ProductDto() {
    }

    public ProductDto(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    @ApiModelProperty(hidden = true)
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Byte getDiscount() {
        return discount;
    }

    public void setDiscount(Byte discount) {
        this.discount = discount;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

}
