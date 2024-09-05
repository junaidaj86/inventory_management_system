package com.zinu.inventory.product;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.zinu.inventory.configuration.TenantedAuthenticationToken;
import com.zinu.inventory.supplier.Supplier;
import com.zinu.inventory.supplier.SupplierRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;

    private Long getTenantId() {
        TenantedAuthenticationToken authentication = (TenantedAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        return authentication.getTenantId();
    }

    public Product createProduct(Product product, Long supplierId) {
        Long tenantId = getTenantId();
       

        Supplier supplier = supplierRepository.findById(supplierId)
                .orElseThrow(() -> new RuntimeException("Supplier not found"));

        if (!supplier.getTenantId().equals(tenantId)) {
            throw new RuntimeException("Supplier does not belong to the current tenant");
        }

        product.setTenantId(tenantId);
        product.setSupplier(supplier);

        return productRepository.save(product);
    }
}
