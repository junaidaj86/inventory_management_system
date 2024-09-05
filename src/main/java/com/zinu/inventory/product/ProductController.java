package com.zinu.inventory.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(
            @RequestBody Product product,
            @RequestParam Long supplierId,
            @RequestParam Long categoryId
    ) {
        ProductDTO createdProduct = productService.createProduct(product, supplierId, categoryId);
        return ResponseEntity.ok(createdProduct);
    }

    @GetMapping("/scan/{barcodeText}")
    public ResponseEntity<Product> getProductByBarcodeAndTenant(
            @PathVariable String barcodeText) {

        Product product = productService.findProductByBarcodeAndTenant(barcodeText);
        return ResponseEntity.ok(product);
    }
}

