package com.fluffy.backend.entity;

import javax.persistence.CascadeType;
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
@Table(name = "Suppliers")
public class Suppliers {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "supplier_id")
	private Long IdSupplier;

	@Column(name = "supplier_name")
	private String name;

	@Column(name = "supplier_segment")
	private String segment;

	@Column(name = "delivery_forecast")
	private String deliveryForecast;

	@Column(name = "cnpj")
	private String cnpj;

	@Column(name = "phone_01")
	private Long phone;

	@Column(name = "address")
	private String address;

	@Column(name = "city")
	private String city;

	@Column(name = "state")
	private String state;

	@Column(name = "status")
	private Integer status;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "payment_method_id")
	private PaymentsMethods paymentsMethods;

	public void setId(Long supplierId) {
		// TODO Auto-generated method stub
		
	}




}
