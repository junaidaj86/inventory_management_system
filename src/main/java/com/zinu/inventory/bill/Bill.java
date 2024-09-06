package com.zinu.inventory.bill;

import java.util.List;

import com.zinu.inventory.product.Product;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean isParked = false;

    @OneToMany(cascade = CascadeType.ALL)
    private List<BilledItem> billedItems;
    
}
