package com.zinu.inventory.supplier;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/suppliers")
@AllArgsConstructor
public class SupplierController {

    private final SupplierService supplierService;

    @PostMapping
    public ResponseEntity<SupplierDTO> createSupplier(@RequestBody Supplier supplier) {
        SupplierDTO createdSupplier = supplierService.createSupplier(supplier);
        return ResponseEntity.ok(createdSupplier);
    }

    @GetMapping
    public ResponseEntity<List<SupplierDTO>> getSupplier() {
        List<SupplierDTO> createdSupplier = supplierService.getSupplier();
        return ResponseEntity.ok(createdSupplier);
    }
}
