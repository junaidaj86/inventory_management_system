package com.zinu.inventory.store;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.zinu.inventory.authentication.Users;
import com.zinu.inventory.product.Product;
import com.zinu.inventory.supplier.Supplier;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Store {

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    @Size(min = 1, max = 100, message = "Store name must be between 1 and 100 characters")
    private String storeName;

    @Column( unique = true, length = 50)
    private String registerNumber;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Users> users;

    // One store can have many products
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Product> products;

    // One store can have many suppliers
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Supplier> suppliers;

}
