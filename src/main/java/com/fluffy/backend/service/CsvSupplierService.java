package com.fluffy.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fluffy.backend.DTO.CsvSupplerData;
import com.fluffy.backend.entity.PaymentsMethods;
import com.fluffy.backend.entity.Stocks;
import com.fluffy.backend.entity.SupplierStockOffer;
import com.fluffy.backend.entity.Suppliers;
import com.fluffy.backend.repository.PaymentsMethodsRepository;
import com.fluffy.backend.repository.StocksRepository;
import com.fluffy.backend.repository.SupplierStockOfferRepository;
import com.fluffy.backend.repository.SuppliersRepository;

@Service
public class CsvSupplierService {
	
	 @Autowired
	    private SuppliersRepository suppliersRepository;

	    @Autowired
	    private PaymentsMethodsRepository paymentsMethodsRepository;

	    @Autowired
	    private SupplierStockOfferRepository supplierFeedstockOfferRepository;
	    
	    @Autowired
	    private StocksRepository feedstocksRepository;

	    public void processCsvData(List<CsvSupplerData> csvDataList) {
	        for (CsvSupplerData csvData : csvDataList) {
	        	
	            Suppliers suppliers = new Suppliers();
	            suppliers.setName(csvData.getName());
	            suppliers.setSegment(csvData.getSegment());
	            suppliers.setDeliveryForecast(csvData.getDeliveryForecast());
	            suppliers.setCnpj(csvData.getCnpj());
	            suppliers.setPhone(csvData.getPhone());
	            suppliers.setAddress(csvData.getAddress());
	            suppliers.setCity(csvData.getCity());
	            suppliers.setState(csvData.getState());
	            suppliers.setStatus(csvData.getStatus());
	            
	           
	            PaymentsMethods paymentsMethods = new PaymentsMethods();
	            paymentsMethods.setName(csvData.getPaymentMethodName());
	            paymentsMethods.setPayDay(csvData.getPaymentMethodPayDay());
	   
	            suppliers.setPaymentsMethods(paymentsMethods);
	            suppliersRepository.saveAndFlush(suppliers);
	            paymentsMethodsRepository.saveAndFlush(paymentsMethods);
	            
	            Stocks feedstocks = new Stocks();
	            feedstocks.setName(csvData.getFeedName());
	            feedstocks.setAmountAvailable(csvData.getAmountAvailable());
	            feedstocks.setMeasurement(csvData.getFeedMeasurement());

	            feedstocksRepository.saveAndFlush(feedstocks);
	            
	            SupplierStockOffer supplierFeedstockOffer = new SupplierStockOffer();
	            supplierFeedstockOffer.setQuantityCan(csvData.getQuantityCan());
	            supplierFeedstockOffer.setMeasurement(csvData.getMeasurement());
	            supplierFeedstockOffer.setValue(csvData.getValue());
	            supplierFeedstockOffer.setSuppliers(suppliers);
	            supplierFeedstockOffer.setStocks(feedstocks);

	            supplierFeedstockOfferRepository.saveAndFlush(supplierFeedstockOffer);
	        }
	    }

}
