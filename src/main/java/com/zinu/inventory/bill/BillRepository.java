package com.zinu.inventory.bill;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
    
    List<Bill> findByIsParked(boolean isParked);
    List<Bill> findByStoreId(Long storeId);
}

