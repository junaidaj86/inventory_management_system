package com.zinu.inventory.store;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Address {

    @Column(length = 100)
    private String buildingNumber;

    @Column(length = 100)
    private String shopNumber;

    @Column(length = 100)
    private String street;

    @Column(nullable = false, length = 10)
    private String zipcode;

    @Column(nullable = false, length = 50)
    private String city;

    @Column(nullable = false, length = 50)
    private String state;

    @Column(nullable = false, length = 50)
    private String country;

}
