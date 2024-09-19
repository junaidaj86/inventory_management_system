package com.zinu.inventory.product;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zinu.inventory.category.Category;
import com.zinu.inventory.common.BaseEntity;
import com.zinu.inventory.store.Store;
import com.zinu.inventory.supplier.Supplier;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Product extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Long tenantId;

    // Link product to a store (tenant)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    @JsonBackReference
    private Store store;

    @Column(nullable = false)
    private double price;

    @Column(nullable = true)
    private double sellingPrice;

    @Column(nullable = false)
    private int stockQuantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id")
    @JsonIgnore
    private Supplier supplier;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "barcode_id", referencedColumnName = "id")
    @JsonIgnore
    private Barcode barcode;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnore
    private Category category;

}

