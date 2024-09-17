package com.zinu.inventory.product;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Find a product by barcode and tenantId
    Optional<Product> findByBarcodeAndTenantId(Barcode barcode, Long tenantId);

    // Search for products by name (case insensitive)
    List<Product> findByNameContainingIgnoreCase(String name);

    // Get paginated list of products for a tenant
    Page<Product> findByTenantId(Long tenantId, Pageable pageable);
}

