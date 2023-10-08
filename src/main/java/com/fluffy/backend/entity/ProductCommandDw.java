//package com.fluffy.backend.entity;
//
//import java.math.BigDecimal;
//import java.sql.Timestamp;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//
//import lombok.Data;
//
//@Data
//@Entity
//@Table(name = "PRODUCT_COMMAND")
//public class ProductCommandDw {
//	
//
//		@Id
//		@Column(name = "PRODUCT_DEMAND_ID")
//		private Integer productDemandId;
//
//		@Column(name = "PC_QUANTITY")
//		private BigDecimal pcQuantity;
//
//		@Column(name = "PC_DATETIME_ORDER")
//		private Timestamp pcDatetimeOrder;
//
//		@ManyToOne
//		@JoinColumn(name = "PRODUCT_ID")
//		private Product product;
//
//		@ManyToOne
//		@JoinColumn(name = "COMMAND_ID")
//		private Command command;
//
//		@Column(name = "PC_TURN")
//		private Integer pcTurn;
//
//		@Column(name = "PC_MEASUREMENT")
//		private String pcMeasurement;
//
//	}
//

