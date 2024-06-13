package com.project.OrderMaster.service;

import com.project.OrderMaster.entity.Product;
import com.project.OrderMaster.kafka.producer.KafkaProducer;
import com.project.OrderMaster.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;


@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private KafkaProducer kafkaProducer;


    public Product create(Product product) {
        Product savedProduct = repository.save(product);
        CompletableFuture.runAsync(() -> kafkaProducer.sendMessage(savedProduct));
        return savedProduct;
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

