package com.zinu.inventory.supply_order;

import com.zinu.inventory.model.SupplierOrder;
import com.zinu.inventory.model.OrderItem;
import lombok.Data;

import java.util.List;

@Data
public class SupplierOrderRequest {
    private SupplierOrder supplierOrder;
    private List<OrderItem> items;
}
