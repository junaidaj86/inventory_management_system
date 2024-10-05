package com.zinu.inventory.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProfitByCategoryDTO {
    private Long categoryId;
    private String categoryName;
    private Double totalProfit;
}

