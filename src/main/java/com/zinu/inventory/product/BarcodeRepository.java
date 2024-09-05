package com.zinu.inventory.product;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BarcodeRepository extends JpaRepository<Barcode, Long> {
    Optional<Barcode> findByCode(String code);
}
