package com.zinu.inventory.productSupplier;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.zinu.inventory.configuration.TenantedAuthenticationToken;
import com.zinu.inventory.model.Product;
import com.zinu.inventory.model.Supplier;

import java.util.List;

@RestController
@RequestMapping("/api/v1/suppliers")
public class SupplierProductController {

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private ProductService productService;

    @PostMapping
     @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Supplier> registerSupplier(@RequestBody Supplier supplier) {
        Supplier createdSupplier = supplierService.registerSupplier(supplier);
        return ResponseEntity.ok(createdSupplier);
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Supplier>> getAllSuppliers() {
          TenantedAuthenticationToken authentication = (TenantedAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        String tenantId = authentication.getTenantId();
        System.out.println("tenant id ="+ tenantId);
        List<Supplier> suppliers = supplierService.getAllSuppliers();
        return ResponseEntity.ok(suppliers);
    }

    @PostMapping("/{supplierId}/products")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Product> addProductToSupplier(@PathVariable Long supplierId,
                                                        @RequestBody Product product) {
        Product createdProduct = productService.addProduct(supplierId, product);
        return ResponseEntity.ok(createdProduct);
    }


}
