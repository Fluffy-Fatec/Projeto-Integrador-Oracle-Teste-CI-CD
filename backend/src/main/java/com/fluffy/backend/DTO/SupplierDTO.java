package com.fluffy.backend.DTO;

import lombok.Data;

@Data

public class SupplierDTO {
    private Long idSupplier;
    private String name;
    private String segment;
    private String deliveryForecast;
    private String cnpj;
    private Long phone;
    private String address;
    private String city;
    private String state;
    private Integer status;
    private String paymentMethodName;
    private Integer paymentMethodPayDay;
    
}
