package com.fluffy.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fluffy.backend.entity.Stocks;

public interface StocksRepository extends JpaRepository<Stocks, Long> {

	@Query("SELECT s, so FROM Stocks s LEFT JOIN SupplierStockOffer so ON s.Idstock = so.stocks.Idstock WHERE s.name = :name")
	List<Object[]> findStocksAndSupplierStockOfferByName(@Param("name") String name);
}
