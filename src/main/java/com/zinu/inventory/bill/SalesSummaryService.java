package com.zinu.inventory.bill;


import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class SalesSummaryService {

    
    private BilledItemRepository billedItemRepository;

    public SalesProfitDTO getSalesAndProfit(LocalDateTime startDate, LocalDateTime endDate) {
        List<Object[]> result = billedItemRepository.calculateTotalSalesAndProfit();
    
        // Assuming there is always one row in the result
        if (!result.isEmpty()) {
            Object[] row = result.get(0);  // First row of results
            Double totalSales = (Double) row[0];  // Cast the first element to Double
            Double totalProfit = (Double) row[1];  // Cast the second element to Double
            
            // Return the DTO with extracted values
            return new SalesProfitDTO(totalSales, totalProfit);
        }
    
        // Return default values if no data is found
        return new SalesProfitDTO(0.0, 0.0);
    }
    
    
}

