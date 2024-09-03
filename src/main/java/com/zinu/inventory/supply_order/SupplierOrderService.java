package com.zinu.inventory.supply_order;

import com.zinu.inventory.model.*;
import com.zinu.inventory.repository.SupplierOrderRepository;
import com.zinu.inventory.repository.OrderItemRepository;
import com.zinu.inventory.repository.PaymentRepository;
import com.zinu.inventory.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zinu.inventory.model.OrderStatus;

import java.util.Date;
import java.util.List;

@Service
public class SupplierOrderService {

    @Autowired
    private SupplierOrderRepository supplierOrderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private DeliveryRepository deliveryRepository;

    public SupplierOrder createOrder(SupplierOrder supplierOrder, List<OrderItem> items) {
        double totalAmount = items.stream().mapToDouble(OrderItem::getTotalPrice).sum();
        supplierOrder.setTotalAmount(totalAmount);
        supplierOrder.setAmountDue(totalAmount);

        SupplierOrder savedOrder = supplierOrderRepository.save(supplierOrder);
        for (OrderItem item : items) {
            item.setSupplierOrder(savedOrder);
            orderItemRepository.save(item);
        }

        return savedOrder;
    }

    public SupplierOrder updatePayment(Long orderId, Double paymentAmount, Boolean isFullPayment) {
        SupplierOrder supplierOrder = supplierOrderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        double newAmountPaid = supplierOrder.getAmountPaid() + paymentAmount;
        supplierOrder.setAmountPaid(newAmountPaid);
        supplierOrder.setAmountDue(supplierOrder.getTotalAmount() - newAmountPaid);

        if (supplierOrder.getAmountDue() == 0) {
            supplierOrder.setStatus(OrderStatus.FULLY_PAID);
        } else {
            supplierOrder.setStatus(OrderStatus.PARTIALLY_PAID);
        }

        paymentRepository.save(Payment.builder()
                .supplierOrder(supplierOrder)
                .amount(paymentAmount)
                .paymentDate(new Date())
                .isFullPayment(isFullPayment)
                .build());

        return supplierOrderRepository.save(supplierOrder);
    }

    public SupplierOrder trackOrder(Long orderId) {
        return supplierOrderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public Delivery createDelivery(Long orderId, Delivery delivery) {
        delivery.setSupplierOrder(supplierOrderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found")));

        return deliveryRepository.save(delivery);
    }

    public Delivery updateDeliveryStatus(Long deliveryId, DeliveryStatus status) {
        Delivery delivery = deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new RuntimeException("Delivery not found"));

        delivery.setStatus(status);
        return deliveryRepository.save(delivery);
    }
}
