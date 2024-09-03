package com.zinu.inventory.supply_order;

import com.zinu.inventory.model.Delivery;
import com.zinu.inventory.model.DeliveryStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/deliveries")
public class DeliveryController {

    @Autowired
    private SupplierOrderService supplierOrderService;

    @PostMapping("/orders/{orderId}")
    public Delivery createDelivery(@PathVariable Long orderId, @RequestBody Delivery delivery) {
        return supplierOrderService.createDelivery(orderId, delivery);
    }

    @PatchMapping("/{deliveryId}")
    public Delivery updateDeliveryStatus(@PathVariable Long deliveryId, @RequestParam DeliveryStatus status) {
        return supplierOrderService.updateDeliveryStatus(deliveryId, status);
    }
}
