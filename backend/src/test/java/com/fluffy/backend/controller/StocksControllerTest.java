package com.fluffy.backend.controller;

import com.fluffy.backend.DTO.StocksAndSupplierStockOfferDTO;
import com.fluffy.backend.DTO.StocksListDTO;
import com.fluffy.backend.entity.Stocks;
import com.fluffy.backend.entity.SupplierStockOffer;
import com.fluffy.backend.service.StocksService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class StocksControllerTest {

    @Mock
    private StocksService stocksService;

    private StocksController stocksController;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        stocksController = new StocksController();
        stocksController.feedstocksService = stocksService;
    }


    @Test
    public void testListAllSupplierStockOffer() {
        List<SupplierStockOffer> supplierStockOfferList = new ArrayList<>();
        Mockito.when(stocksService.getAllSupplierStockOffer()).thenReturn(supplierStockOfferList);

        List<SupplierStockOffer> result = stocksController.listAllSupplierStockOffer();

        assertEquals(supplierStockOfferList, result);
    }

    @Test
    public void testFindStocksAndSupplierStockOfferByName() {
        String name = "TestStock";
        List<Object[]> resultData = new ArrayList<>();

        Stocks stocks = new Stocks();
        SupplierStockOffer supplierStockOffer = new SupplierStockOffer();

        resultData.add(new Object[]{stocks, supplierStockOffer});

        Mockito.when(stocksService.findStocksAndSupplierStockOfferByName(eq(name))).thenReturn(resultData);

        ResponseEntity<List<StocksAndSupplierStockOfferDTO>> result = stocksController.findStocksAndSupplierStockOfferByName(name);

        List<StocksAndSupplierStockOfferDTO> resultDtos = result.getBody();
        assertEquals(1, resultDtos.size());
        assertEquals(stocks, resultDtos.get(0).getStocks());
        assertEquals(supplierStockOffer, resultDtos.get(0).getSupplierStockOffer());
    }

    @Test
    public void testCreateStock() {
        Stocks newStock = new Stocks();
        Stocks createdStock = new Stocks();

        Mockito.when(stocksService.createStock(any(Stocks.class))).thenReturn(createdStock);

        ResponseEntity<Stocks> result = stocksController.createStock(newStock);

        assertEquals(createdStock, result.getBody());
    }
}
