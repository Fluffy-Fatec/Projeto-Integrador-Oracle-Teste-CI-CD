package com.fluffy.backend.entity;

import java.sql.Timestamp;
import java.util.Date;

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
@Table(name = "Stock_Product")
public class StockProduct {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sp_id")
    private Long IdStockProduct;

    @Column(name = "datetime_ready", nullable = false)
    private Timestamp datetimeReady;

    @Column(name = "sp_amount_contained", nullable = false)
    private Double amountContained;

    @ManyToOne
    @JoinColumn(name = "stock_id", nullable = false)
    private Stocks stocks;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "pc_measurement", nullable = false)
    private String measurement;
}
