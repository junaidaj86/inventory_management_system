package com.zinu.inventory.bill;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zinu.inventory.product.Product;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class BilledItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "bill_id")
    @JsonIgnore
    private Bill bill;

    @ManyToOne
    private Product product;

    private int quantity;
}
