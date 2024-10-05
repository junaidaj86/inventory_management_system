package com.zinu.inventory.bill;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zinu.inventory.DTO.DashboardDTO;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/dashboard")
@AllArgsConstructor
public class DashboardController {

    private final SalesSummaryService salesSummaryService;

    @GetMapping
    public DashboardDTO getDashboardData() {
        return salesSummaryService.getDashboardData();
    }
}
