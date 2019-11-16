package com.laptopshop.service.impl;

import com.laptopshop.dto.BrandDto;
import com.laptopshop.entity.Brand;
import com.laptopshop.exception.BadRequestException;
import com.laptopshop.exception.ResourceNotFoundException;
import com.laptopshop.repository.BrandRepository;
import com.laptopshop.service.BrandService;
import org.apache.logging.log4j.util.Strings;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<BrandDto> getAll() {
        return brandRepository.getAll();
    }

    @Override
    public BrandDto getById(Integer id) throws ResourceNotFoundException {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Brand.class, id));
        return modelMapper.map(brand, BrandDto.class);
    }

    @Override
    public BrandDto getByName(String name) throws ResourceNotFoundException {
        Brand brand = getByNameOptional(name)
                .orElseThrow(() -> new ResourceNotFoundException(Brand.class, "name", name));
        return modelMapper.map(brand, BrandDto.class);
    }

    private Optional<Brand> getByNameOptional(String name) {
        return brandRepository.findByName(name);
    }

    @Override
    public BrandDto create(BrandDto newBrand) throws BadRequestException {
        String newBrandName = newBrand.getName();
        if (Strings.isEmpty(newBrandName)) {
            throw new BadRequestException("Brand's name is required");
        }

        Optional<Brand> optionalBrand = getByNameOptional(newBrandName);
        if (optionalBrand.isPresent()) {
            throw new BadRequestException("Brand's name: " + newBrandName + " is already existed");
        }

        Brand createdBrand = brandRepository.save(modelMapper.map(newBrand, Brand.class));
        return modelMapper.map(createdBrand, BrandDto.class);
    }

    @Override
    public BrandDto update(Integer id, BrandDto brand) throws ResourceNotFoundException, BadRequestException {
        Brand oldBrand = brandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Brand.class, id));

        String newBrandName = brand.getName();
        if (Strings.isNotEmpty(newBrandName)) {
            Optional<Brand> possibleDuplicateName = getByNameOptional(newBrandName);
            if (possibleDuplicateName.isPresent()) {
                throw new BadRequestException("Brand's name: " + newBrandName + " is already existed");
            }

            oldBrand.setName(newBrandName);
        }

        if (Strings.isNotBlank(brand.getLogoImagePath())) {
            oldBrand.setLogoImagePath(brand.getLogoImagePath());
        }

        return modelMapper.map(brandRepository.save(oldBrand), BrandDto.class);
    }

    @Override
    public void deleteById(Integer id) {
        Optional<Brand> brand = brandRepository.findById(id);
        if (brand.isPresent()) {
            brandRepository.deleteById(id);
        }
    }

}
