package com.zinu.inventory.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TopProductsDTO {
    private Long productId;
    private String productName;
    private Double totalSales;
}
