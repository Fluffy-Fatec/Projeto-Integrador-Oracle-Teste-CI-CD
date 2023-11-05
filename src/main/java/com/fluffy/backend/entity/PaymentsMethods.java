package com.fluffy.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Entity
@Table(name = "Payments_methods")
public class PaymentsMethods {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Column(name = "pm_id")
	private Long idPm;
	
	@Column(name = "pm_name")
	private String name;
	
	@Column(name = "payday")
	private Integer payDay;

}
