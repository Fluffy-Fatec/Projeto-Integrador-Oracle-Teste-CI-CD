package com.fluffy.backend.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "Supplier_Stock_Offer")
public class SupplierStockOffer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sf_offer_id")
	private Long IdSfOffer;

	@ManyToOne
	@JoinColumn(name = "supplier_id")
	private Suppliers suppliers;

	@ManyToOne
	@JoinColumn(name = "stock_id")
	private Stocks stocks;

	@Column(name = "quantity_can_supply", nullable = false)
	private Double quantityCan;

	@Column(name = "pc_measurement", nullable = false)
	private String measurement;

	@Column(name = "sf_offer_value", nullable = false)
	private BigDecimal value;


}
