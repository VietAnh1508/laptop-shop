package com.laptopshop.service;

import com.laptopshop.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> getAll();

    Product save(Product product);

    Optional<Product> getById(Long id);

    void delete(Long id);

}
