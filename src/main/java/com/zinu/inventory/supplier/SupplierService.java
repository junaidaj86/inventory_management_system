package com.zinu.inventory.supplier;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.zinu.inventory.configuration.TenantedAuthenticationToken;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SupplierService {

    private final SupplierRepository supplierRepository;

    private Long getTenantId() {
        TenantedAuthenticationToken authentication = (TenantedAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        return authentication.getTenantId();
    }

    public Supplier createSupplier(Supplier supplier) {
        Long tenantId = getTenantId();
        supplier.setTenantId(tenantId);
        return supplierRepository.save(supplier);
    }

    public List<Supplier> getSupplier(){
        return supplierRepository.findByTenantId(getTenantId());
    }
}

