//package com.fluffy.backend.entity;
//
//import java.math.BigDecimal;
//import java.sql.Timestamp;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;
//
//import lombok.Data;
//
//@Entity
//@Data
//@Table(name = "PRODUCTS")
//public class ProductDw {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "PRODUCT_ID")
//	private Integer productId;
//
//	@Column(name = "PRODUCT_NAME", nullable = false)
//	private String productName;
//
//	@Column(name = "PRODUCT_VALUE", nullable = false, precision = 10, scale = 2)
//	private BigDecimal productValue;
//
//	@Column(name = "PRODUCT_TYPE", nullable = false, length = 50)
//	private String productType;
//
////	@Column(name = "DATETIME_UPLOAD", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
////	private Timestamp datetimeUpload;
//
//}
