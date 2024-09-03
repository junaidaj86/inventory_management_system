package com.zinu.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zinu.inventory.model.Barcode;

public interface BarcodeRepository extends JpaRepository<Barcode, Long> {
}
