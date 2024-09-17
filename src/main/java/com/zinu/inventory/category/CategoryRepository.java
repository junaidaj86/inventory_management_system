package com.zinu.inventory.category;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zinu.inventory.store.Store;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByStore(Store store);
    Optional<Category> findByIdAndStoreId(Long id, Long storeId);
}
