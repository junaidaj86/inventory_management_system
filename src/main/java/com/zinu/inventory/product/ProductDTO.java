package com.zinu.inventory.product;

import com.zinu.inventory.category.Category;
import com.zinu.inventory.supplier.Supplier;

import lombok.Data;

@Data
public class ProductDTO {

    private Long id;
    private String name;
    private String description;
    private Long tenantId;
    private double price;
    private int stockQuantity;
    private Barcode barcode;
    private Supplier supplier;
    private Category category;

    // Add constructor to map from Product entity to ProductDTO
    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.tenantId = product.getTenantId();
        this.price = product.getPrice();
        this.stockQuantity = product.getStockQuantity();
        this.barcode = product.getBarcode();  // eager fetch or handle lazy loading
        this.supplier = product.getSupplier();  // eager fetch or handle lazy loading
        this.category = product.getCategory();
    }
}
