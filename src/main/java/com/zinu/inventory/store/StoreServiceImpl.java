package com.zinu.inventory.store;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StoreServiceImpl implements StoreService{

    private final StoreRepository storeRepository;

    @PostMapping
    public Store createStore(@RequestBody Store store) {
        try {
            return storeRepository.save(store);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Store with the same register number already exists.");
        }
    }

    @GetMapping
    public List<Store> getStores() {
        return storeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Store> getStoreById(@PathVariable Long id) {
        return storeRepository.findById(id);
    }

    @PutMapping("/{id}")
    public Store updateStore(@PathVariable Long id, @RequestBody Store store) {
        if (!storeRepository.existsById(id)) {
            throw new RuntimeException("Store not found with ID: " + id);
        }
        store.setId(id); 
        return storeRepository.save(store);
    }

    @DeleteMapping("/{id}")
    public void deleteStore(@PathVariable Long id) {
        if (!storeRepository.existsById(id)) {
            throw new RuntimeException("Store not found with ID: " + id);
        }
        storeRepository.deleteById(id);
    }

    @GetMapping("/name/{storeName}")
    public List<Store> getStoresByName(@PathVariable String storeName) {
        return storeRepository.findByStoreName(storeName);
    }

    @GetMapping("/register/{registerNumber}")
    public List<Store> getStoresByRegisterNumber(@PathVariable String registerNumber) {
        return storeRepository.findByRegisterNumber(registerNumber);
    }

    @GetMapping("/count")
    public long countStores() {
        return storeRepository.count();
    }
}
