package com.zinu.inventory.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PurchaseSummaryDTO {
    private Double totalPurchase;
    private Double totalCost;
}

