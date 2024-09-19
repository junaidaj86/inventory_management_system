package com.zinu.inventory.product;

import lombok.Data;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private double price;
    private int stockQuantity;
    private String barcode;
    private Long categoryId;
    private Long supplierId;

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getSellingPrice();
        this.stockQuantity = product.getStockQuantity();
        this.barcode = product.getBarcode().getCode();
        this.categoryId = product.getCategory().getId();
        this.supplierId = product.getSupplier().getId();
    }
}
