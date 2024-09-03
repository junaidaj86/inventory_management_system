package com.zinu.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.zinu.inventory.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}