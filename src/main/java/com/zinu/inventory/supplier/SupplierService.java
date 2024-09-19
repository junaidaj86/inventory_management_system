package com.zinu.inventory.supplier;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.zinu.inventory.configuration.TenantedAuthenticationToken;
import com.zinu.inventory.product.ProductDTO;
import com.zinu.inventory.store.Store;
import com.zinu.inventory.store.StoreRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SupplierService {

    private final SupplierRepository supplierRepository;
    private final StoreRepository storeRepository;

    private Long getTenantId() {
        TenantedAuthenticationToken authentication = (TenantedAuthenticationToken) SecurityContextHolder.getContext()
                .getAuthentication();
        return authentication.getTenantId();
    }

    public SupplierDTO createSupplier(Supplier supplier) {
        Long tenantId = getTenantId();
        supplier.setTenantId(tenantId);
        Store store = storeRepository.findById(tenantId)
                .orElseThrow(() -> new RuntimeException("Store not found for tenantId: " + tenantId));
        supplier.setStore(store);
        Supplier resultSupplier = supplierRepository.save(supplier);
        return new SupplierDTO(resultSupplier);
    }

    public List<SupplierDTO> getSupplier() {
    List<Supplier> suppliers = supplierRepository.findByTenantId(getTenantId());
    return suppliers.stream()
            .map(SupplierDTO::new) // Use the SupplierDTO constructor to map each supplier
            .collect(Collectors.toList()); // Collect the results into a List
}


    public Supplier getSupplierById(Long supplierId) {
        return supplierRepository.findById(supplierId)
                .orElseThrow(() -> new RuntimeException("Supplier not found with ID: " + supplierId));
    }

    public Supplier updateSupplier(Long supplierId, Supplier supplierDetails) {
        Supplier existingSupplier = supplierRepository.findById(supplierId)
                .orElseThrow(() -> new RuntimeException("Supplier not found with ID: " + supplierId));

        existingSupplier.setName(supplierDetails.getName());
        existingSupplier.setAddress(supplierDetails.getAddress());
        // Set other fields accordingly

        return supplierRepository.save(existingSupplier);
    }

    public void deleteSupplier(Long supplierId) {
        Supplier existingSupplier = supplierRepository.findById(supplierId)
                .orElseThrow(() -> new RuntimeException("Supplier not found with ID: " + supplierId));
        supplierRepository.delete(existingSupplier);
    }

    public List<Supplier> searchSuppliersByName(String name) {
        return supplierRepository.findByNameContainingIgnoreCaseAndTenantId(name, getTenantId());
    }
    
}
