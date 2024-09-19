package com.zinu.inventory.bill;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/sales")
@AllArgsConstructor
public class SalesSummaryController {

    
    private SalesSummaryService salesSummaryService;

    @GetMapping("/summary")
    public SalesProfitDTO getSalesSummary(@RequestParam Optional<String> startDate, 
                                          @RequestParam Optional<String> endDate) {
        LocalDateTime start = startDate.isPresent() ? LocalDateTime.parse(startDate.get()) : LocalDateTime.now().minusMonths(1);
        LocalDateTime end = endDate.isPresent() ? LocalDateTime.parse(endDate.get()) : LocalDateTime.now();
        return salesSummaryService.getSalesAndProfit(start, end);
    }
}

