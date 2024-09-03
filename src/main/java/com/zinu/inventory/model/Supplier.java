package com.zinu.inventory.model;


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

    @Column(nullable = false)
    private String contactInfo;

    private String tenantId;
}

