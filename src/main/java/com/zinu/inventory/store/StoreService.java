package com.zinu.inventory.store;

import java.util.List;
import java.util.Optional;

public interface StoreService {
    Store createStore(Store store);
    List<Store> getStores();
    Optional<Store> getStoreById(Long id);
    Store updateStore(Long id, Store store);
    void deleteStore(Long id);
    List<Store> getStoresByName(String storeName);
    List<Store> getStoresByRegisterNumber(String registerNumber);
    long countStores();
}
