package com.zinu.inventory.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SalesSummaryDTO {
    private Double totalSales;
    private Double totalProfit;
}
