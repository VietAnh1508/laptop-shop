package com.laptopshop.service.impl;

import com.laptopshop.entity.Product;
import com.laptopshop.exception.ResourceNotFoundException;
import com.laptopshop.repository.ProductRepository;
import com.laptopshop.service.ProductService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product getById(Long id) throws ResourceNotFoundException {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Product.class, id));
    }

    @Override
    public Product create(Product newProduct) {
        return productRepository.save(newProduct);
    }

    @Override
    public Product update(Long id, Product product) throws ResourceNotFoundException {
        Product productToBeUpdated = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Product.class, id));

        productToBeUpdated.setName(product.getName());
        productToBeUpdated.setBrand(product.getBrand());
        productToBeUpdated.setCategory(product.getCategory());
        productToBeUpdated.setPrice(product.getPrice());
        productToBeUpdated.setDiscount(product.getDiscount());

        return productRepository.save(productToBeUpdated);
    }

    @Override
    public void delete(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            productRepository.deleteById(id);
        }
    }

}
