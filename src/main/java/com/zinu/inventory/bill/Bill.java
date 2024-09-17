package com.zinu.inventory.bill;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zinu.inventory.product.Product;
import com.zinu.inventory.store.Store;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    @JsonIgnore
    private Store store;
    
}
