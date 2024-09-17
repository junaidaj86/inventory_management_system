package com.zinu.inventory.bill;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zinu.inventory.configuration.TenantedAuthenticationToken;
import com.zinu.inventory.exception.InsufficientStockException;
import com.zinu.inventory.exception.ResourceNotFoundException;
import com.zinu.inventory.product.Product;
import com.zinu.inventory.product.ProductRepository;
import com.zinu.inventory.store.Store;
import com.zinu.inventory.store.StoreRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BillService {

    private final BillRepository billRepository;
    private final StoreRepository storeRepository;
    private final ProductRepository productRepository;

    // Get the current tenant ID from authentication
    private Long getTenantId() {
        TenantedAuthenticationToken authentication = (TenantedAuthenticationToken) SecurityContextHolder.getContext()
                .getAuthentication();
        return authentication.getTenantId();
    }

    @Transactional
    public Bill createNewBill() {
        Store store = storeRepository.findById(getTenantId())
                .orElseThrow(() -> new ResourceNotFoundException("Store not found with id: " + getTenantId()));

        Bill bill = new Bill();
        bill.setParked(false);
        bill.setStore(store); // Associate with store
        return billRepository.save(bill);
    }

    @Transactional
    public Bill addItemToBill(Long billId, BilledItem item) {
        Bill bill = billRepository.findById(billId)
                .orElseThrow(() -> new ResourceNotFoundException("Bill not found with id: " + billId));

        // Fetch product to check stock
        Product product = productRepository.findById(item.getProduct().getId())
                .orElseThrow(
                        () -> new ResourceNotFoundException("Product not found with id: " + item.getProduct().getId()));

        // Check if enough stock is available
        if (product.getStockQuantity() < item.getQuantity()) {
            throw new InsufficientStockException("Not enough stock for product: " + product.getName());
        }

        // Deduct stock quantity
        product.setStockQuantity(product.getStockQuantity() - item.getQuantity());
        productRepository.save(product);

        item.setBill(bill); // Associate item with bill
        bill.getBilledItems().add(item);
        return billRepository.save(bill);
    }

    public Bill parkBill(Long billId) {
        Bill bill = billRepository.findById(billId)
                .orElseThrow(() -> new ResourceNotFoundException("Bill not found with id: " + billId));

        bill.setParked(true); // Mark bill as parked
        return billRepository.save(bill);
    }

    public Bill unParkBill(Long billId) {
        Bill bill = billRepository.findById(billId)
                .orElseThrow(() -> new ResourceNotFoundException("Bill not found with id: " + billId));

        bill.setParked(false); // Mark bill as parked
        return billRepository.save(bill);
    }

    public List<Bill> getParkedBills() {
        return billRepository.findByIsParked(true);
    }
}
