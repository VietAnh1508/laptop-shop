package com.laptopshop.service.impl;

import com.laptopshop.entity.Brand;
import com.laptopshop.exception.ResourceNotFoundException;
import com.laptopshop.repository.BrandRepository;
import com.laptopshop.service.BrandService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public List<Brand> getAll() {
        return brandRepository.findAll();
    }

    @Override
    public Optional<Brand> getById(Integer id) {
        return brandRepository.findById(id);
    }

    @Override
    public Brand create(Brand newBrand) {
        return brandRepository.save(newBrand);
    }

    @Override
    public Brand update(Brand brand) throws ResourceNotFoundException {
        Brand oldBrand = brandRepository.findById(brand.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Brand not found for this id: " + brand.getId()));

        oldBrand.setName(brand.getName());
        oldBrand.setLogoImagePath(brand.getLogoImagePath());

        return brandRepository.save(oldBrand);
    }

    @Override
    public void deleteById(Integer id) {
        brandRepository.deleteById(id);
    }

}
