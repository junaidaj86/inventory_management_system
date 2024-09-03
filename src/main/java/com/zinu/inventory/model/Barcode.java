package com.zinu.inventory.model;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Barcode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   
    private String code;

    private String tenantId;
}

