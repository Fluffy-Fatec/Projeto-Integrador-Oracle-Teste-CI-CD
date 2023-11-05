package com.fluffy.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fluffy.backend.entity.StockProduct;

public interface StockProductRepository extends JpaRepository<StockProduct, Long> {

}
