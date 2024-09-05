package com.zinu.inventory.supplier;


import com.zinu.inventory.store.Address;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Embedded
    private Address address;

    private Long tenantId;
}

