package com.zinu.inventory.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zinu.inventory.model.Product;
import com.zinu.inventory.productSupplier.ProductService;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    @Autowired
    private ProductService productService;

     @PostMapping("/{supplierId}/products")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Product> addProduct(@PathVariable Long supplierId,
                                                        @RequestBody Product product) {
        Product createdProduct = productService.addProduct(supplierId, product);
        return ResponseEntity.ok(createdProduct);
    }
    
}
