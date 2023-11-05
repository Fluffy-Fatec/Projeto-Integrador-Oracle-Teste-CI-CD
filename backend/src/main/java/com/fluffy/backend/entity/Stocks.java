package com.fluffy.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "Stock")
public class Stocks {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "stock_id")
	private Long Idstock;

	@Column(name = "stock_item_name", nullable = false)
	private String name;

	@Column(name = "amount_availble", nullable = false)
	private Double amountAvailable;

	@Column(name = "pc_measurement", nullable = false)
	private String measurement;

	
}
