package com.zinu.inventory.product;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BarcodeRepository extends JpaRepository<Barcode, Long> {
    Optional<Barcode> findByCode(String code);
}
