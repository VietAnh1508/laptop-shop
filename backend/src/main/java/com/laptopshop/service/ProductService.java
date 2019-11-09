package com.laptopshop.service;

import com.laptopshop.entity.Product;
import com.laptopshop.exception.ResourceNotFoundException;

import java.util.List;

public interface ProductService {

    List<Product> getAll();

    Product getById(Long id) throws ResourceNotFoundException;

    Product create(Product newProduct);

    Product update(Long id, Product product) throws ResourceNotFoundException;

    void delete(Long id);

}
