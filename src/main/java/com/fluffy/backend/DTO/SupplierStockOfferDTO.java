package com.fluffy.backend.DTO;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class SupplierStockOfferDTO {
	private Long supplierId;
	private Long stockId;
	private Double quantityCan;
	private String measurement;
	private BigDecimal value;

}
