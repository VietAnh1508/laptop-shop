package com.laptopshop.service;

import com.laptopshop.dto.BrandDto;
import com.laptopshop.exception.BadRequestException;
import com.laptopshop.exception.ResourceNotFoundException;

import java.util.List;

public interface BrandService {

    List<BrandDto> getAll();

    BrandDto getById(Integer id) throws ResourceNotFoundException;

    BrandDto getByName(String name) throws ResourceNotFoundException;

    BrandDto create(BrandDto newBrand) throws BadRequestException;

    BrandDto update(Integer id, BrandDto brand) throws ResourceNotFoundException, BadRequestException;

    void deleteById(Integer id);

}
