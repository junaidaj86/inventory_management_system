package com.zinu.inventory.product;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Find a product by barcode and tenantId
    Optional<Product> findByBarcodeAndTenantId(Barcode barcode, Long tenantId);

    // Search for products by name (case insensitive)
    List<Product> findByNameContainingIgnoreCase(String name);

    // Get paginated list of products for a tenant
    Page<Product> findByTenantId(Long tenantId, Pageable pageable);

    // reporting queries
    
    // Total Products Count in a Date Range
    @Query("SELECT COUNT(p) FROM Product p WHERE p.createdDate BETWEEN :startDate AND :endDate")
    long countTotalProductsInRange(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    // Total Purchase Amount in a Date Range (assuming price * stockQuantity)
    @Query("SELECT SUM(p.price * p.stockQuantity) FROM Product p WHERE p.createdDate BETWEEN :startDate AND :endDate")
    double sumTotalPurchaseAmountInRange(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    // Total Purchase by Category in a Date Range
    @Query("SELECT p.category.name, SUM(p.price * p.stockQuantity) FROM Product p WHERE p.createdDate BETWEEN :startDate AND :endDate GROUP BY p.category.name")
    List<Object[]> sumPurchaseAmountByCategoryInRange(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    // Total Purchase by Supplier in a Date Range
    @Query("SELECT p.supplier.name, SUM(p.price * p.stockQuantity) FROM Product p WHERE p.createdDate BETWEEN :startDate AND :endDate GROUP BY p.supplier.name")
    List<Object[]> sumPurchaseAmountBySupplierInRange(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    // Total Purchase by Store in a Date Range
    @Query("SELECT p.store.storeName, SUM(p.price * p.stockQuantity) FROM Product p WHERE p.createdDate BETWEEN :startDate AND :endDate GROUP BY p.store.storeName")
    List<Object[]> sumPurchaseAmountByStoreInRange(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    // Total Purchase by Month in a Date Range
    @Query("SELECT DATE_TRUNC('month', p.createdDate), SUM(p.price * p.stockQuantity) FROM Product p WHERE p.createdDate BETWEEN :startDate AND :endDate GROUP BY DATE_TRUNC('month', p.createdDate)")
    List<Object[]> sumPurchaseAmountByMonthInRange(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

}
