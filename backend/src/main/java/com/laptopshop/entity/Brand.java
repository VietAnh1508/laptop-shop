package com.laptopshop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "brand")
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Brand name is required")
    private String name;

    private String logoImagePath;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "brand")
    private List<Product> productList;

    public Brand() {
    }

    public Brand(String name, String logoImagePath) {
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

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

}
