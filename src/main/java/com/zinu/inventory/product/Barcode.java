package com.zinu.inventory.product;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Barcode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code;

    private String tenantId;
}

