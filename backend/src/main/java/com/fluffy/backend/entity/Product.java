package com.fluffy.backend.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "PRODUCTS")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private Long idProduct;

	@Column(name = "product_name")
	private String name;

	@Column(name = "product_status")
	private String status;

	@Column(name = "product_value")
	private BigDecimal value;

	@Column(name = "product_type")
	private String type;

}