package com.zinu.inventory.reports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/summary/purchase")
public class PurchaseSummaryController {

    @Autowired
    private PurchaseSummaryService purchaseSummaryService;

    @GetMapping
    public PurchaseSummaryDTO getPurchaseSummary() {
        return purchaseSummaryService.getPurchaseSummary();
    }
}

