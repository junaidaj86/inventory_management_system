package com.zinu.inventory.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OutOfStockProductsDTO {
    private Long productId;
    private String productName;
}

