package com.zinu.inventory.bill;

import org.springframework.stereotype.Service;

import com.zinu.inventory.DTO.DashboardDTO;
import com.zinu.inventory.DTO.ExpenseSummaryByCategoryDTO;
import com.zinu.inventory.DTO.ExpenseSummaryDTO;
import com.zinu.inventory.DTO.OutOfStockProductsDTO;
import com.zinu.inventory.DTO.ProfitByCategoryDTO;
import com.zinu.inventory.DTO.PurchaseSummaryDTO;
import com.zinu.inventory.DTO.SalesSummaryDTO;
import com.zinu.inventory.DTO.TopProductsDTO;
import com.zinu.inventory.product.ProductRepository;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.ArrayList;

@Service
@AllArgsConstructor
public class SalesSummaryService {

    private final BilledItemRepository billedItemRepository;
    private final ProductRepository productRepository;

    public DashboardDTO getDashboardData() {
        // Fetch all necessary data for the dashboard
        List<TopProductsDTO> topProducts = getTopPopularProducts(5);
        SalesSummaryDTO salesSummary = getSalesSummary();
        PurchaseSummaryDTO purchaseSummary = getPurchaseSummary();
        ExpenseSummaryDTO expenseSummary = getExpenseSummary();
        List<ExpenseSummaryByCategoryDTO> expenseSummaryByCategory = getExpenseSummaryByCategory();
        List<ProfitByCategoryDTO> profitByCategory = getProfitByCategory();
        List<OutOfStockProductsDTO> outOfStockProducts = getOutOfStockProducts();

        return new DashboardDTO(topProducts, salesSummary, purchaseSummary, expenseSummary,
                expenseSummaryByCategory, profitByCategory, outOfStockProducts);
    }

    // Top 5 popular products
    public List<TopProductsDTO> getTopPopularProducts(int limit) {
        List<Object[]> results = billedItemRepository.findTopProducts(limit);
        List<TopProductsDTO> topProducts = new ArrayList<>();
        for (Object[] result : results) {
            Long productId = (Long) result[0];
            String productName = (String) result[1];
            Double totalSales = (Double) result[2];
            topProducts.add(new TopProductsDTO(productId, productName, totalSales));
        }
        return topProducts;
    }

    // Sales summary
    public SalesSummaryDTO getSalesSummary() {
        List<Object[]> result = billedItemRepository.calculateTotalSalesAndProfit();
        if (!result.isEmpty()) {
            Object[] row = result.get(0);
            Double totalSales = (Double) row[0];
            Double totalProfit = (Double) row[1];
            return new SalesSummaryDTO(totalSales, totalProfit);
        }
        return new SalesSummaryDTO(0.0, 0.0);
    }

    // Purchase summary (implement logic based on your schema)
    public PurchaseSummaryDTO getPurchaseSummary() {
        // Mocked data; replace with actual logic
        return new PurchaseSummaryDTO(5000.0, 4000.0);
    }

    // Expense summary (implement logic based on your schema)
    public ExpenseSummaryDTO getExpenseSummary() {
        // Mocked data; replace with actual logic
        return new ExpenseSummaryDTO(2000.0);
    }

    // Expense summary by category
    public List<ExpenseSummaryByCategoryDTO> getExpenseSummaryByCategory() {
        List<Object[]> results = billedItemRepository.calculateExpenseSummaryByCategory();
        List<ExpenseSummaryByCategoryDTO> expenseByCategory = new ArrayList<>();
        for (Object[] result : results) {
            Long categoryId = (Long) result[0];
            String categoryName = (String) result[1];
            Double totalExpenses = (Double) result[2];
            expenseByCategory.add(new ExpenseSummaryByCategoryDTO(categoryId, categoryName, totalExpenses));
        }
        return expenseByCategory;
    }

    // Profit by category
    public List<ProfitByCategoryDTO> getProfitByCategory() {
        List<Object[]> results = billedItemRepository.calculateProfitByCategory();
        List<ProfitByCategoryDTO> profitByCategory = new ArrayList<>();
        for (Object[] result : results) {
            Long categoryId = (Long) result[0];
            String categoryName = (String) result[1];
            Double totalProfit = (Double) result[2];
            profitByCategory.add(new ProfitByCategoryDTO(categoryId, categoryName, totalProfit));
        }
        return profitByCategory;
    }

    // Products out of stock
    public List<OutOfStockProductsDTO> getOutOfStockProducts() {
        List<Object[]> results = productRepository.findOutOfStockProducts();
        List<OutOfStockProductsDTO> outOfStockProducts = new ArrayList<>();
        for (Object[] result : results) {
            Long productId = (Long) result[0];
            String productName = (String) result[1];
            outOfStockProducts.add(new OutOfStockProductsDTO(productId, productName));
        }
        return outOfStockProducts;
    }
}
