package com.zinu.inventory.bill;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/bills")
@AllArgsConstructor
public class BillController {

    private final BillService billService;

    @PostMapping("/new")
    public ResponseEntity<Bill> createNewBill() {
        Bill newBill = billService.createNewBill();
        return ResponseEntity.ok(newBill);
    }

    @PostMapping("/{billId}/add-item")
    public ResponseEntity<Bill> addItemToBill(@PathVariable Long billId, @RequestBody List<BilledItem> items) {
        Bill updatedBill = billService.addItemsToBill(billId, items);
        return ResponseEntity.ok(updatedBill);
    }

    @PutMapping("/{billId}/park")
    public ResponseEntity<Bill> parkBill(@PathVariable Long billId) {
        Bill parkedBill = billService.parkBill(billId);
        return ResponseEntity.ok(parkedBill);
    }

    @GetMapping("/parked")
    public ResponseEntity<List<Bill>> getParkedBills() {
        List<Bill> parkedBills = billService.getParkedBills();
        return ResponseEntity.ok(parkedBills);
    }

    @PutMapping("/{billId}/unpark")
    public ResponseEntity<Void> unparkBill(@PathVariable Long billId) {
        billService.unParkBill(billId);
        return ResponseEntity.noContent().build();
    }

    // @DeleteMapping("/{billId}")
    // public ResponseEntity<Void> deleteBill(@PathVariable Long billId) {
    //     billService.deleteBill(billId);
    //     return ResponseEntity.noContent().build();
    // }
}
