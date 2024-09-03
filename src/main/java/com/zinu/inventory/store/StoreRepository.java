package com.zinu.inventory.store;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long>{
    List<Store> findByStoreName(String storeName);
    List<Store> findByRegisterNumber(String registerNumber);
}
