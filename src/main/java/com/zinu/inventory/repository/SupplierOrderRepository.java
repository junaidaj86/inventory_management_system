package com.zinu.inventory.repository;

import com.zinu.inventory.model.SupplierOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierOrderRepository extends JpaRepository<SupplierOrder, Long> {
}
