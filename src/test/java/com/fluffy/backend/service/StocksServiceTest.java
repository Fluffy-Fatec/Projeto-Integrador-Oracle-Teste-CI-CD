package com.fluffy.backend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.fluffy.backend.DTO.StocksListDTO;
import com.fluffy.backend.entity.Stocks;
import com.fluffy.backend.entity.SupplierStock;
import com.fluffy.backend.entity.SupplierStockOffer;
import com.fluffy.backend.entity.Suppliers;
import com.fluffy.backend.repository.StocksRepository;
import com.fluffy.backend.repository.SupplierStockOfferRepository;
import com.fluffy.backend.repository.SupplierStockRepository;

public class StocksServiceTest {
	@InjectMocks
	private StocksService feedstocksService;

	@Mock
	private StocksRepository feedstocksRepository;

	@Mock
	private SupplierStockRepository supplierFeedstockRepository;
	
	@Mock
    private SupplierStockOfferRepository supplierStockOfferRepository;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetAllFeedstocks() {

		List<Stocks> feedstocksList = new ArrayList<>();

		Stocks feedstock1 = new Stocks();
		feedstock1.setName("Feedstock1");
		feedstock1.setAmountAvailable(100.0);
		feedstock1.setMeasurement("Kg");

		Stocks feedstock2 = new Stocks();
		feedstock2.setName("Feedstock2");
		feedstock2.setAmountAvailable(50.0);
		feedstock2.setMeasurement("Liters");

		feedstocksList.add(feedstock1);
		feedstocksList.add(feedstock2);

		SupplierStock supplierfeedstock = new SupplierStock();
		supplierfeedstock.setPrice(Double.valueOf("50"));

		when(feedstocksRepository.findAll()).thenReturn(feedstocksList);
		when(supplierFeedstockRepository.findByStocks(Mockito.any())).thenReturn(supplierfeedstock);

		List<StocksListDTO> feedstocksListDTO = feedstocksService.getAllFeedstocks();

		assertEquals(2, feedstocksListDTO.size()); // Verifique o tamanho da lista
		assertEquals("Feedstock1", feedstocksListDTO.get(0).getName()); // Verifique o nome do primeiro feedstock
		assertEquals(100.0, feedstocksListDTO.get(0).getAmountAvailable()); // Verifique a quantidade disponível do
																			// primeiro
		// feedstock
		assertEquals("Kg", feedstocksListDTO.get(0).getMeasurement()); // Verifique a unidade de medida do primeiro
																		// feedstock
	}
	
	 @Test
	    public void testGetAllSupplierStockOffer() {
	        List<SupplierStockOffer> supplierStockOffers = new ArrayList<>();
	        supplierStockOffers.add(new SupplierStockOffer());
	        supplierStockOffers.add(new SupplierStockOffer());

	        when(supplierStockOfferRepository.findAll()).thenReturn(supplierStockOffers);

	        List<SupplierStockOffer> result = feedstocksService.getAllSupplierStockOffer();

	        assertNotNull(result);
	        assertEquals(supplierStockOffers.size(), result.size());
	    }
    
	@Test
	public void testFindStocksAndSupplierStockOfferByName() {
		String name = "ExampleName";
		List<Object[]> resultList = new ArrayList<>();
		Mockito.when(feedstocksRepository.findStocksAndSupplierStockOfferByName(name)).thenReturn(resultList);
		List<Object[]> result = feedstocksService.findStocksAndSupplierStockOfferByName(name);
		assertNotNull(result);
	    assertEquals(resultList, result);
	}

	@Test
    public void testCreateStock() {
        Stocks newStock = new Stocks();
        newStock.setName("ExampleStock");
        newStock.setAmountAvailable(100.0);
        newStock.setMeasurement("kg");

        when(feedstocksRepository.save(any(Stocks.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Stocks result = feedstocksService.createStock(newStock);

        verify(feedstocksRepository, times(1)).save(newStock);

        assertNotNull(result);
        assertEquals(newStock.getName(), result.getName());
        assertEquals(newStock.getAmountAvailable(), result.getAmountAvailable(), 0.01); 
        assertEquals(newStock.getMeasurement(), result.getMeasurement());
    }

}
