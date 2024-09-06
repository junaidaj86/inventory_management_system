package com.zinu.inventory.product;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByTenantId(Long tenantId);
     // Find a product by barcode and tenantId
    Optional<Product> findByBarcodeAndTenantId(Barcode barcode, Long tenantId);
    List<Product> findByNameContainingIgnoreCase(String name);
}

