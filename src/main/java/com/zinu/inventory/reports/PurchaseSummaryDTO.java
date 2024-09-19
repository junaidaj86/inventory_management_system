package com.zinu.inventory.reports;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class PurchaseSummaryDTO {
    private long totalProducts;
    private double totalPurchaseAmount;
    private List<Map<String, Object>> totalPurchaseByCategory;
    private List<Map<String, Object>> totalPurchaseBySupplier;
    private List<Map<String, Object>> totalPurchaseByStore;
    private List<Map<String, Object>> totalPurchaseByMonth;
}

