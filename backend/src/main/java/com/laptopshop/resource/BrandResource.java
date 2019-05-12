package com.laptopshop.resource;

import com.laptopshop.entity.Brand;
import com.laptopshop.service.BrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "Brand")
@RestController
@RequestMapping("/brand")
public class BrandResource {

    private BrandService brandService;

    public BrandResource(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Get all brands")
    public ResponseEntity<List<Brand>> getAllBrands() {
        List<Brand> brands = brandService.getAll();
        return new ResponseEntity<>(brands, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Add a new brand")
    public ResponseEntity<Brand> addNewBrand(@RequestBody Brand newBrand) {
        Brand createdBrand = brandService.save(newBrand);
        return new ResponseEntity<>(createdBrand, HttpStatus.CREATED);
    }

}
