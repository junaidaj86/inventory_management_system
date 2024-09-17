package com.zinu.inventory.category;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.zinu.inventory.configuration.TenantedAuthenticationToken;
import com.zinu.inventory.store.Store;
import com.zinu.inventory.store.StoreRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final StoreRepository storeRepository;

    private Long getTenantId() {
        TenantedAuthenticationToken authentication = (TenantedAuthenticationToken) SecurityContextHolder.getContext()
                .getAuthentication();
        return authentication.getTenantId();
    }

    // Create a new category
    public Category createCategory(Category category) {
        Store store = storeRepository.findById(getTenantId())
                .orElseThrow(() -> new RuntimeException("Store not found with ID: " + getTenantId()));

        category.setStore(store); // Link category to the store
        return categoryRepository.save(category);
    }

    // Get all categories
    public List<Category> getAllCategories() {
        Store store = storeRepository.findById(getTenantId())
                .orElseThrow(() -> new RuntimeException("Store not found with ID: " + getTenantId()));

        return categoryRepository.findByStore(store);
    }

    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findByIdAndStoreId(id, getTenantId());
    }

    // Update a category by ID for a specific store
    @Transactional
    public Category updateCategory(Long categoryId, Category updatedCategory) {
        Category category = categoryRepository.findByIdAndStoreId(categoryId, getTenantId())
                .orElseThrow(() -> new RuntimeException("Category not found with ID: " + categoryId + " for store ID: " + getTenantId()));

        category.setName(updatedCategory.getName());
        return categoryRepository.save(category);
    }

    // Delete category by ID and storeId
    @Transactional
    public void deleteCategory(Long categoryId) {
        Category category = categoryRepository.findByIdAndStoreId(categoryId, getTenantId())
                .orElseThrow(() -> new RuntimeException("Category not found with ID: " + categoryId + " for store ID: " + getTenantId()));

        categoryRepository.delete(category);
    }
}
