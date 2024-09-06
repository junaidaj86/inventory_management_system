package com.zinu.inventory.bill;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zinu.inventory.exception.ResourceNotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BillService {

    
    private BillRepository billRepository;

    public Bill createNewBill() {
        Bill bill = new Bill();
        bill.setParked(false);  // Mark as an active bill
        return billRepository.save(bill);
    }

    public Bill addItemToBill(Long billId, BilledItem item) {
        Bill bill = billRepository.findById(billId)
                .orElseThrow(() -> new ResourceNotFoundException("Bill not found with id: " + billId));

        bill.getBilledItems().add(item);
        return billRepository.save(bill);
    }

    public Bill parkBill(Long billId) {
        Bill bill = billRepository.findById(billId)
                .orElseThrow(() -> new ResourceNotFoundException("Bill not found with id: " + billId));

        bill.setParked(true);  // Mark bill as parked
        return billRepository.save(bill);
    }

    public List<Bill> getParkedBills() {
        return billRepository.findByIsParked(true);
    }
}

