package com.project.OrderMaster.repository;

import com.project.OrderMaster.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
