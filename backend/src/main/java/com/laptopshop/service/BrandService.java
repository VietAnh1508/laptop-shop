package com.laptopshop.service;

import com.laptopshop.entity.Brand;
import com.laptopshop.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface BrandService {

    List<Brand> getAll();

    Optional<Brand> getById(Integer id);

    Brand create(Brand newBrand);

    Brand update(Integer id, Brand brand) throws ResourceNotFoundException;

    void deleteById(Integer id);

}
