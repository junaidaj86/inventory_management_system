package com.zinu.inventory.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class DashboardDTO {
    private List<TopProductsDTO> topProducts; // Top 5 popular products
    private SalesSummaryDTO salesSummary;      // Sales Summary
    private PurchaseSummaryDTO purchaseSummary;   // Purchase Summary
    private ExpenseSummaryDTO expenseSummary;    // Expense Summary
    private List<ExpenseSummaryByCategoryDTO> expenseSummaryByCategory; // Expense by Category
    private List<ProfitByCategoryDTO> profitByCategory;         // Profit by Category
    private List<OutOfStockProductsDTO> outOfStockProducts;     // Products out of stock
}

