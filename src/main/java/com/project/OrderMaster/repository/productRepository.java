package com.project.OrderMaster.repository;

import com.project.OrderMaster.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface productRepository extends JpaRepository<Product, Long> {

}
