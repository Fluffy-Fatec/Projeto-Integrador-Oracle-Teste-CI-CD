package com.fluffy.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fluffy.backend.DTO.SupplierStockOfferDTO;
import com.fluffy.backend.entity.Stocks;
import com.fluffy.backend.entity.SupplierStockOffer;
import com.fluffy.backend.entity.Suppliers;
import com.fluffy.backend.repository.StocksRepository;
import com.fluffy.backend.repository.SupplierStockOfferRepository;
import com.fluffy.backend.repository.SuppliersRepository;

@Service
public class SupplierStockOfferService {
	 @Autowired
	    private SupplierStockOfferRepository supplierStockOfferRepository1;

	    @Autowired
	    private SuppliersRepository suppliersRepository;

	    @Autowired
	    private StocksRepository stocksRepository;

	    public SupplierStockOffer createSupplierStockOffer(SupplierStockOfferDTO supplierStockOfferDTO) {
	        Suppliers supplier = suppliersRepository.findById(supplierStockOfferDTO.getSupplierId())
	            .orElseThrow(() -> new IllegalArgumentException("Supplier not found"));
	        Stocks stock = stocksRepository.findById(supplierStockOfferDTO.getStockId())
	            .orElseThrow(() -> new IllegalArgumentException("Stock not found"));

	        SupplierStockOffer newSupplierStockOffer = new SupplierStockOffer();
	        newSupplierStockOffer.setSuppliers(supplier);
	        newSupplierStockOffer.setStocks(stock);
	        newSupplierStockOffer.setQuantityCan(supplierStockOfferDTO.getQuantityCan());
	        newSupplierStockOffer.setMeasurement(supplierStockOfferDTO.getMeasurement());
	        newSupplierStockOffer.setValue(supplierStockOfferDTO.getValue());

	        return supplierStockOfferRepository1.save(newSupplierStockOffer);
	    }

	    public List<SupplierStockOffer> getAllSupplierStockOffer() {
	        return supplierStockOfferRepository1.findAll();
	    }
	    public void deleteSupplierStockOfferPorId(Long id) {
	        SupplierStockOffer supplierStockOffer = supplierStockOfferRepository1.findById(id)
	            .orElseThrow(() -> new IllegalArgumentException("SupplierStockOffer not found"));
	        supplierStockOfferRepository1.delete(supplierStockOffer);
	    }
}
