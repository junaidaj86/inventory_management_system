package com.zinu.inventory.store;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/store")
@AllArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @PostMapping
    @PreAuthorize("hasRole('SUPERADMIN')")
    public ResponseEntity<Store> createStore(@RequestBody Store store) {
        Store createdStore = storeService.createStore(store);
        return new ResponseEntity<>(createdStore, HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasRole('SUPERADMIN') or hasRole('ADMIN')")
    public ResponseEntity<List<Store>> getStores() {
        List<Store> stores = storeService.getStores();
        return new ResponseEntity<>(stores, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('SUPERADMIN') or hasRole('ADMIN')")
    public ResponseEntity<Store> getStoreById(@PathVariable Long id) {
        Optional<Store> store = storeService.getStoreById(id);
        return store.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('SUPERADMIN') or hasRole('ADMIN')")
    public ResponseEntity<Store> updateStore(@PathVariable Long id, @RequestBody Store store) {
        try {
            Store updatedStore = storeService.updateStore(id, store);
            return new ResponseEntity<>(updatedStore, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('SUPERADMIN')")
    public ResponseEntity<Void> deleteStore(@PathVariable Long id) {
        try {
            storeService.deleteStore(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/name/{storeName}")
    @PreAuthorize("hasRole('SUPERADMIN') or hasRole('ADMIN')")
    public ResponseEntity<List<Store>> getStoresByName(@PathVariable String storeName) {
        List<Store> stores = storeService.getStoresByName(storeName);
        return new ResponseEntity<>(stores, HttpStatus.OK);
    }

    @GetMapping("/register/{registerNumber}")
    @PreAuthorize("hasRole('SUPERADMIN') or hasRole('ADMIN')")
    public ResponseEntity<List<Store>> getStoresByRegisterNumber(@PathVariable String registerNumber) {
        List<Store> stores = storeService.getStoresByRegisterNumber(registerNumber);
        return new ResponseEntity<>(stores, HttpStatus.OK);
    }

    @GetMapping("/count")
    @PreAuthorize("hasRole('SUPERADMIN')")
    public ResponseEntity<Long> countStores() {
        long count = storeService.countStores();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
}
