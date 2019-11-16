package com.laptopshop.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.laptopshop.entity.Product;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class BrandDto {

    private Integer id;

    @NotBlank(message = "Brand name is required")
    private String name;

    private String logoImagePath;

    private List<Product> productList;

    public BrandDto() {
    }

    public BrandDto(String name, String logoImagePath) {
        this.name = name;
        this.logoImagePath = logoImagePath;
    }

    public BrandDto(Integer id, String name, String logoImagePath) {
        this.id = id;
        this.name = name;
        this.logoImagePath = logoImagePath;
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

    public String getLogoImagePath() {
        return logoImagePath;
    }

    public void setLogoImagePath(String logoImagePath) {
        this.logoImagePath = logoImagePath;
    }

    @JsonIgnore
    public List<Product> getProductList() {
        return productList;
    }

    @ApiModelProperty(hidden = true)
    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

}
