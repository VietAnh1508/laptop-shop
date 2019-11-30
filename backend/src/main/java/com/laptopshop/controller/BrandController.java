package com.laptopshop.controller;

import com.laptopshop.dto.BrandDto;
import com.laptopshop.exception.BadRequestException;
import com.laptopshop.exception.ResourceNotFoundException;
import com.laptopshop.service.BrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "Brand")
@RestController
@RequestMapping("/brands")
public class BrandController {

    private BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Get all brands")
    public ResponseEntity<List<BrandDto>> getAllBrands() {
        List<BrandDto> brands = brandService.getAll();
        return ResponseEntity.ok(brands);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Get brand by id")
    public ResponseEntity<BrandDto> getBrandById(@PathVariable Integer id) throws ResourceNotFoundException {
        BrandDto brand = brandService.getById(id);
        return ResponseEntity.ok(brand);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Add a new brand")
    public ResponseEntity<BrandDto> addNewBrand(@Valid @RequestBody BrandDto newBrand) throws BadRequestException {
        BrandDto createdBrand = brandService.create(newBrand);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBrand);
    }

    @PutMapping(
            value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Update brand")
    public ResponseEntity<BrandDto> updateBrand(@PathVariable Integer id, @RequestBody BrandDto brand)
            throws ResourceNotFoundException, BadRequestException {
        BrandDto updatedBrand = brandService.update(id, brand);
        return ResponseEntity.ok(updatedBrand);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Delete brand by id")
    public ResponseEntity<Void> deleteBrand(@PathVariable Integer id) {
        brandService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
