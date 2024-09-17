package com.zinu.inventory.product;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.zinu.inventory.category.Category;
import com.zinu.inventory.category.CategoryRepository;
import com.zinu.inventory.configuration.TenantedAuthenticationToken;
import com.zinu.inventory.supplier.Supplier;
import com.zinu.inventory.supplier.SupplierRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;
    private final CategoryRepository categoryRepository;
    private final BarcodeRepository barcodeRepository;

    // Get the current tenant ID from authentication
    private Long getTenantId() {
        TenantedAuthenticationToken authentication = (TenantedAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        return authentication.getTenantId();
    }

    // Create a new product
    public ProductDTO createProduct(Product product, Long supplierId, Long categoryId) {
        Long tenantId = getTenantId();  // Get tenantId from the current user

        Supplier supplier = supplierRepository.findById(supplierId)
                .orElseThrow(() -> new RuntimeException("Supplier not found"));

        if (!supplier.getTenantId().equals(tenantId)) {
            throw new RuntimeException("Supplier does not belong to the current tenant");
        }

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        if (!category.getStore().getId().equals(tenantId)) {
            throw new RuntimeException("Category does not belong to the current tenant's store");
        }

        product.setCategory(category);
        product.setTenantId(tenantId);
        product.setSupplier(supplier);

        // Generate a unique barcode
        String barcodeText = UUID.randomUUID().toString();
        Barcode barcode = new Barcode();
        barcode.setCode(barcodeText);
        barcode.setTenantId(tenantId.toString());  // Link barcode to tenant
        product.setBarcode(barcode);  // Link barcode to the product

        Product savedProduct = productRepository.save(product);
        return new ProductDTO(savedProduct);
    }

    // Update an existing product
    public ProductDTO updateProduct(Long productId, Product productDetails, Long supplierId, Long categoryId) {
        Long tenantId = getTenantId();
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Supplier supplier = supplierRepository.findById(supplierId)
                .orElseThrow(() -> new RuntimeException("Supplier not found"));

        if (!supplier.getTenantId().equals(tenantId)) {
            throw new RuntimeException("Supplier does not belong to the current tenant");
        }

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        if (!category.getStore().getId().equals(tenantId)) {
            throw new RuntimeException("Category does not belong to the current tenant's store");
        }

        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());
        product.setStockQuantity(productDetails.getStockQuantity());
        product.setSupplier(supplier);
        product.setCategory(category);

        Product updatedProduct = productRepository.save(product);
        return new ProductDTO(updatedProduct);
    }

    // Delete a product
    public void deleteProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        productRepository.delete(product);
    }

    // Find product by barcode and tenantId
    public Product findProductByBarcodeAndTenant(String barcodeText) {
        Barcode barcode = barcodeRepository.findByCode(barcodeText)
                .orElseThrow(() -> new IllegalArgumentException("Barcode not found"));

        return productRepository.findByBarcodeAndTenantId(barcode, getTenantId())
                .orElseThrow(() -> new IllegalArgumentException("Product not found for the given barcode and tenant"));
    }

    // Search for products by name
    public List<Product> searchProductByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    // Get paginated list of products for the tenant
    public Page<Product> getProductsPaginated(int page, int size) {
        return productRepository.findByTenantId(getTenantId(), PageRequest.of(page, size));
    }

    // Update stock level for a product
    public ProductDTO updateStock(Long productId, int stockChange) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setStockQuantity(product.getStockQuantity() + stockChange);  // Adjust stock quantity

        Product updatedProduct = productRepository.save(product);
        return new ProductDTO(updatedProduct);
    }
}
