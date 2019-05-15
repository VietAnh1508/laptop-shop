package com.laptopshop.resource;

import com.laptopshop.entity.Brand;
import com.laptopshop.exception.ResourceNotFoundException;
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
@RequestMapping("/brands")
public class BrandResource {

    private BrandService brandService;

    @Autowired
    public BrandResource(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Get all brands")
    public ResponseEntity getAllBrands() {
        List<Brand> brands = brandService.getAll();
        return ResponseEntity.ok(brands);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Add a new brand")
    public ResponseEntity addNewBrand(@RequestBody Brand newBrand) {
        Brand createdBrand = brandService.create(newBrand);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBrand);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Update brand")
    public ResponseEntity editBrand(@RequestBody Brand brand) {
        try {
            Brand updatedBrand = brandService.update(brand);
            return ResponseEntity.ok().body(updatedBrand);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Delete brand by id")
    public ResponseEntity deleteBrand(@PathVariable Integer id) {
        brandService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
