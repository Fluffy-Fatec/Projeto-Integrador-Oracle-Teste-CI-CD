package com.fluffy.backend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.fluffy.backend.DTO.SupplierStockOfferDTO;
import com.fluffy.backend.entity.Stocks;
import com.fluffy.backend.entity.SupplierStockOffer;
import com.fluffy.backend.entity.Suppliers;
import com.fluffy.backend.repository.StocksRepository;
import com.fluffy.backend.repository.SupplierStockOfferRepository;
import com.fluffy.backend.repository.SuppliersRepository;

public class SupplierStockOfferServiceTest {

	@InjectMocks
	private SupplierStockOfferService supplierStockOfferService;

	@Mock
	private SupplierStockOfferRepository supplierStockOfferRepository;

	@Mock
	private SuppliersRepository suppliersRepository;

	@Mock
	private StocksRepository stocksRepository;

	@Test
	public void testCreateSupplierStockOffer() {
		Long supplierId = 1L;
		Long stockId = 2L;

		SupplierStockOfferDTO supplierStockOfferDTO = new SupplierStockOfferDTO();
		supplierStockOfferDTO.setSupplierId(supplierId);
		supplierStockOfferDTO.setStockId(stockId);
		supplierStockOfferDTO.setQuantityCan(100.0);
		supplierStockOfferDTO.setMeasurement("Kg");
		supplierStockOfferDTO.setValue(new BigDecimal("50.0"));

		Suppliers supplier = new Suppliers();
		supplier.setId(supplierId);

		Stocks stock = new Stocks();
		stock.setIdstock(stockId);

		when(suppliersRepository.findById(supplierId)).thenReturn(Optional.of(supplier));
		when(stocksRepository.findById(stockId)).thenReturn(Optional.of(stock));

		SupplierStockOffer newSupplierStockOffer = new SupplierStockOffer();
		newSupplierStockOffer.setSuppliers(supplier);
		newSupplierStockOffer.setStocks(stock);
		newSupplierStockOffer.setQuantityCan(100.0);
		newSupplierStockOffer.setMeasurement("Kg");
		newSupplierStockOffer.setValue(new BigDecimal("50.0"));

		when(supplierStockOfferRepository.save(any(SupplierStockOffer.class))).thenReturn(newSupplierStockOffer);

		SupplierStockOffer result = supplierStockOfferService.createSupplierStockOffer(supplierStockOfferDTO);

		assertNotNull(result);
		assertEquals(supplier, result.getSuppliers());
		assertEquals(stock, result.getStocks());
		assertEquals(new BigDecimal("100.0"), result.getQuantityCan());
		assertEquals("Kg", result.getMeasurement());
		assertEquals(new BigDecimal("50.0"), result.getValue());
	}
	
	 @Test
	    public void testGetAllSupplierStockOffer() {
	        List<SupplierStockOffer> supplierStockOffers = new ArrayList<>();
	        supplierStockOffers.add(new SupplierStockOffer());
	        supplierStockOffers.add(new SupplierStockOffer());

	        when(supplierStockOfferRepository.findAll()).thenReturn(supplierStockOffers);

	        List<SupplierStockOffer> result = supplierStockOfferService.getAllSupplierStockOffer();

	        assertNotNull(result);
	        assertEquals(supplierStockOffers.size(), result.size());
	    }

	@Test
	public void testDeleteSupplierStockOfferPorId() {
		Long id = 1L;

		SupplierStockOffer supplierStockOffer = new SupplierStockOffer();
		supplierStockOffer.setIdSfOffer(id);

		when(supplierStockOfferRepository.findById(id)).thenReturn(Optional.of(supplierStockOffer));

		supplierStockOfferService.deleteSupplierStockOfferPorId(id);

		verify(supplierStockOfferRepository, times(1)).delete(supplierStockOffer);
	}

}
