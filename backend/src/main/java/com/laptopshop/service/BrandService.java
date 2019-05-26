package com.laptopshop.service;

import com.laptopshop.entity.Brand;
import com.laptopshop.exception.BadRequestException;
import com.laptopshop.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface BrandService {

    List<Brand> getAll();

    Optional<Brand> getById(Integer id);

    Optional<Brand> getByName(String name);

    Brand create(Brand newBrand) throws BadRequestException;

    Brand update(Integer id, Brand brand) throws ResourceNotFoundException, BadRequestException;

    void deleteById(Integer id);

}
