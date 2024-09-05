package com.zinu.inventory.category;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

import com.zinu.inventory.product.Product;

@Entity
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Product> products;

   

}
