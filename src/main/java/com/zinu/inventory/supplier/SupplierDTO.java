package com.zinu.inventory.supplier;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zinu.inventory.store.Address;
import com.zinu.inventory.store.Store;

import lombok.Data;

@Data
public class SupplierDTO {
    private Long id;
    private String name;
    private Address address;
    private Long tenantId;
    @JsonIgnore
    private Store store;


public SupplierDTO(Supplier supplier){
    this.id = supplier.getId();
    this.name = supplier.getName();
    this.address = supplier.getAddress();
    this.tenantId = supplier.getTenantId();
    this.store = supplier.getStore();
}
}
