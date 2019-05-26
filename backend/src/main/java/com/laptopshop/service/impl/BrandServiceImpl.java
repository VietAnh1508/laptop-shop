package com.laptopshop.service.impl;

import com.laptopshop.entity.Brand;
import com.laptopshop.exception.BadRequestException;
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
    public Optional<Brand> getByName(String name) {
        return brandRepository.findByName(name);
    }

    @Override
    public Brand create(Brand newBrand) throws BadRequestException {
        Optional<Brand> optionalBrand = getByName(newBrand.getName());
        if (optionalBrand.isPresent()) {
            throw new BadRequestException("Brand's name: " + newBrand.getName() + " is already existed");
        }
        return brandRepository.save(newBrand);
    }

    @Override
    public Brand update(Integer id, Brand brand) throws ResourceNotFoundException, BadRequestException {
        Brand oldBrand = brandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Brand.class, id));

        Optional<Brand> possibleDuplicateName = getByName(brand.getName());
        if (possibleDuplicateName.isPresent()) {
            throw new BadRequestException("Brand's name: " + brand.getName() + " is already existed");
        }

        oldBrand.setName(brand.getName());
        oldBrand.setLogoImagePath(brand.getLogoImagePath());

        return brandRepository.save(oldBrand);
    }

    @Override
    public void deleteById(Integer id) {
        Optional<Brand> brand = brandRepository.findById(id);
        if (brand.isPresent()) {
            brandRepository.deleteById(id);
        }
    }

}
