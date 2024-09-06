package com.zinu.inventory.product;

import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
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

    private Long getTenantId() {
        TenantedAuthenticationToken authentication = (TenantedAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        return authentication.getTenantId();
    }

    public ProductDTO createProduct(Product product, Long supplierId, Long categoryId) {
        Long tenantId = getTenantId();
       

        Supplier supplier = supplierRepository.findById(supplierId)
                .orElseThrow(() -> new RuntimeException("Supplier not found"));

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        if (!supplier.getTenantId().equals(tenantId)) {
            throw new RuntimeException("Supplier does not belong to the current tenant");
        }
        product.setCategory(category);
        product.setTenantId(tenantId);
        product.setSupplier(supplier);

        // Generate a unique barcode using the product name
        String barcodeText = UUID.randomUUID().toString();
        //String barcodeImagePath = barcodeService.generateBarcode(barcodeText);

        // Save the barcode details in the Barcode entity
        Barcode barcode = new Barcode();
        barcode.setCode(barcodeText);
        //barcode.setImagePath(barcodeImagePath);
        product.setBarcode(barcode); 
        Product prod = productRepository.save(product);
        return new ProductDTO(product);
        
    }

    public Product findProductByBarcodeAndTenant(String barcodeText) {
        // Find the Barcode entity by the barcode code
        Barcode barcode = barcodeRepository.findByCode(barcodeText)
                .orElseThrow(() -> new IllegalArgumentException("Barcode not found"));

        // Find the Product by Barcode and tenantId
        return productRepository.findByBarcodeAndTenantId(barcode, getTenantId())
                .orElseThrow(() -> new IllegalArgumentException("Product not found for the given barcode and tenant"));
    }

    // public String generateBarcode(String barcodeText) throws Exception {
    //     private static final String BARCODE_DIRECTORY = "barcodes/";
    //     BitMatrix bitMatrix = new MultiFormatWriter().encode(barcodeText, BarcodeFormat.CODE_128, 300, 150);
    //     String barcodeFileName = BARCODE_DIRECTORY + barcodeText + ".png";
    //     Path path = Paths.get(barcodeFileName);
    //     MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
    //     return barcodeFileName; // Return the path to the barcode image
    // }

    public List<Product> searchProductByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }
}
