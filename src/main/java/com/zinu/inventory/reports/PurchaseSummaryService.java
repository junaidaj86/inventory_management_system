package com.zinu.inventory.reports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zinu.inventory.product.ProductRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PurchaseSummaryService {

    @Autowired
    private ProductRepository productRepository;

    public PurchaseSummaryDTO getPurchaseSummary() {
        PurchaseSummaryDTO summaryDTO = new PurchaseSummaryDTO();

        // Total Products
        // summaryDTO.setTotalProducts(productRepository.countTotalProducts());

        // // Total Purchase Amount
        // summaryDTO.setTotalPurchaseAmount(productRepository.sumTotalPurchaseAmount());

        // // Total Purchase by Category
        // summaryDTO.setTotalPurchaseByCategory(productRepository.sumPurchaseAmountByCategory().stream()
        //         .map(record -> Map.of("category", record[0], "totalAmount", record[1]))
        //         .collect(Collectors.toList()));

        // // Total Purchase by Supplier
        // summaryDTO.setTotalPurchaseBySupplier(productRepository.sumPurchaseAmountBySupplier().stream()
        //         .map(record -> Map.of("supplier", record[0], "totalAmount", record[1]))
        //         .collect(Collectors.toList()));

        // // Total Purchase by Store
        // summaryDTO.setTotalPurchaseByStore(productRepository.sumPurchaseAmountByStore().stream()
        //         .map(record -> Map.of("store", record[0], "totalAmount", record[1]))
        //         .collect(Collectors.toList()));

        // // Total Purchase by Month
        // summaryDTO.setTotalPurchaseByMonth(productRepository.sumPurchaseAmountByMonth().stream()
        //         .map(record -> Map.of("month", record[0], "totalAmount", record[1]))
        //         .collect(Collectors.toList()));

        return summaryDTO;
    }
}
