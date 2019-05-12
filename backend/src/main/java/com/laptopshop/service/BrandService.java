package com.laptopshop.service;

import com.laptopshop.entity.Brand;

import java.util.List;

public interface BrandService {

    List<Brand> getAll();

    Brand save(Brand newBrand);

}
