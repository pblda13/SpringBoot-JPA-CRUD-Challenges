package com.project.OrderMaster.service;

import com.project.OrderMaster.entity.Product;
import com.project.OrderMaster.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;


    public Product create(Product product) {
        return repository.save(product);
    }

    public Product update(Long id, Product product) {
        Product existingProduct = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id));

        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());

        return repository.save(existingProduct);
    }

    public List<Product> getAll() {
        List<Product> list = repository.findAll();
        if (!list.isEmpty()) {
            return list;
        } else {
            throw new RuntimeException("No products found");
        }
    }

    public Optional<Product> getById(Long id) {
        return repository.findById(id);
    }

    public void delete(Long id) {
        Product existingProduct = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id));
        repository.delete(existingProduct);
    }
}

