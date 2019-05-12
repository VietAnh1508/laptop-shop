package com.laptopshop.service.impl;

import com.laptopshop.entity.Brand;
import com.laptopshop.repository.BrandRepository;
import com.laptopshop.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class BrandServiceImpl implements BrandService {

    private BrandRepository brandRepository;

    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public List<Brand> getAll() {
        return brandRepository.findAll();
    }

    @Override
    public Brand save(Brand newBrand) {
        return brandRepository.save(newBrand);
    }

}
