package com.zinu.inventory.supply_order;

import com.zinu.inventory.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/supplier-orders")
public class SupplierOrderController {

    @Autowired
    private SupplierOrderService supplierOrderService;

    @PostMapping
    public SupplierOrder createOrder(@RequestBody SupplierOrderRequest request) {
        SupplierOrder supplierOrder = request.getSupplierOrder();
        List<OrderItem> items = request.getItems();
        return supplierOrderService.createOrder(supplierOrder, items);
    }

    @PostMapping("/{orderId}/payments")
    public SupplierOrder makePayment(@PathVariable Long orderId, @RequestParam Double amount, @RequestParam Boolean isFullPayment) {
        return supplierOrderService.updatePayment(orderId, amount, isFullPayment);
    }

    @GetMapping("/{orderId}")
    public SupplierOrder trackOrder(@PathVariable Long orderId) {
        return supplierOrderService.trackOrder(orderId);
    }
}
