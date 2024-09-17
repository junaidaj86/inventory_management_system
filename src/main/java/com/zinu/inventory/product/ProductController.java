package com.zinu.inventory.product;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    // Endpoint to create a product
    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(
            @RequestBody Product product,
            @RequestParam Long supplierId,
            @RequestParam Long categoryId) {
        try {
            ProductDTO createdProduct = productService.createProduct(product, supplierId, categoryId);
            return ResponseEntity.ok(createdProduct);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null); // Handle errors during product creation
        }
    }

    // Endpoint to update a product
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(
            @PathVariable Long id,
            @RequestBody Product productDetails,
            @RequestParam Long supplierId,
            @RequestParam Long categoryId) {
        try {
            ProductDTO updatedProduct = productService.updateProduct(id, productDetails, supplierId, categoryId);
            return ResponseEntity.ok(updatedProduct);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null); // Handle errors during product update
        }
    }

    // Endpoint to delete a product
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        try {
            productService.deleteProduct(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build(); // Handle errors during deletion
        }
    }

    // Endpoint to get a product by barcode and tenant
    @GetMapping("/scan/{barcodeText}")
    public ResponseEntity<Product> getProductByBarcodeAndTenant(
            @PathVariable String barcodeText) {
        try {
            Product product = productService.findProductByBarcodeAndTenant(barcodeText);
            return ResponseEntity.ok(product);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build(); // Handle barcode not found
        }
    }

    // Search for products by name
    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProductByName(
            @RequestParam String name) {
        List<Product> products = productService.searchProductByName(name);
        return ResponseEntity.ok(products);
    }

    // Endpoint to get paginated products
    @GetMapping
    public ResponseEntity<Page<Product>> getProductsPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Product> products = productService.getProductsPaginated(page, size);
        return ResponseEntity.ok(products);
    }

    // Endpoint to update the stock of a product
    @PutMapping("/{id}/stock")
    public ResponseEntity<ProductDTO> updateStock(
            @PathVariable Long id,
            @RequestParam int stockChange) {
        try {
            ProductDTO updatedProduct = productService.updateStock(id, stockChange);
            return ResponseEntity.ok(updatedProduct);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null); // Handle stock update errors
        }
    }
}