package com.fluffy.backend.DTO;

import com.fluffy.backend.entity.Stocks;
import com.fluffy.backend.entity.SupplierStockOffer;

import lombok.Data;

@Data
public class StocksAndSupplierStockOfferDTO {
	private Stocks stocks;
	private SupplierStockOffer supplierStockOffer;

	public StocksAndSupplierStockOfferDTO(Stocks stocks, SupplierStockOffer supplierStockOffer) {
		this.stocks = stocks;
		this.supplierStockOffer = supplierStockOffer;
	}
}
