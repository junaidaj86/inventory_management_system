package com.zinu.inventory.category;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zinu.inventory.common.BaseEntity;
import com.zinu.inventory.product.Product;
import com.zinu.inventory.store.Store;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Category extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Product> products;

    // Optional: Link category to a store (tenant) if categories are store-specific
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    @JsonIgnore
    private Store store;

}
