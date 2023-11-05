package com.fluffy.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fluffy.backend.entity.Stocks;
import com.fluffy.backend.entity.SupplierStock;

@Repository
public interface SupplierStockRepository extends JpaRepository<SupplierStock, Long>{
	
	SupplierStock findByStocks(Stocks stocks);

}
