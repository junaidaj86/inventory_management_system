package com.zinu.inventory.bill;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/bills")
@AllArgsConstructor
public class BillController {

    
    private BillService billService;

    // Create a new billing session
    @PostMapping("/new")
    public ResponseEntity<Bill> createNewBill() {
        Bill newBill = billService.createNewBill();
        return ResponseEntity.ok(newBill);
    }

    // Add a product to a bill
    @PostMapping("/{billId}/add-item")
    public ResponseEntity<Bill> addItemToBill(@PathVariable Long billId, @RequestBody BilledItem item) {
        Bill updatedBill = billService.addItemToBill(billId, item);
        return ResponseEntity.ok(updatedBill);
    }

    // Park the current bill
    @PutMapping("/{billId}/park")
    public ResponseEntity<Bill> parkBill(@PathVariable Long billId) {
        Bill parkedBill = billService.parkBill(billId);
        return ResponseEntity.ok(parkedBill);
    }

    // Resume a parked bill
    @GetMapping("/parked")
    public ResponseEntity<List<Bill>> getParkedBills() {
        List<Bill> parkedBills = billService.getParkedBills();
        return ResponseEntity.ok(parkedBills);
    }
}

