package com.zinu.inventory.productSupplier;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zinu.inventory.model.Product;
import com.zinu.inventory.model.Supplier;
import com.zinu.inventory.repository.ProductRepository;
import com.zinu.inventory.repository.SupplierRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    public Product addProduct(Long supplierId, Product product) {
        Supplier supplier = supplierRepository.findById(supplierId)
                .orElseThrow(() -> new RuntimeException("Supplier not found"));
        product.setSupplier(supplier);
        return productRepository.save(product);
    }
}
