package com.fluffy.backend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fluffy.backend.DTO.StocksAndSupplierStockOfferDTO;
import com.fluffy.backend.DTO.StocksListDTO;
import com.fluffy.backend.entity.Stocks;
import com.fluffy.backend.entity.SupplierStockOffer;
import com.fluffy.backend.service.StocksService;

@CrossOrigin
@RestController
@RequestMapping("/api/stocks")
public class StocksController {

	@Autowired
	StocksService feedstocksService;
	@Autowired
	private StocksService stocksService;

	 @GetMapping()
	    public List<Stocks> getAllStocks() {
	        return stocksService.getAllStocks();
	    }
	@GetMapping("/StockOffer")
	public List<SupplierStockOffer> listAllSupplierStockOffer() {
		return feedstocksService.getAllSupplierStockOffer();
	}

	@GetMapping("/{name}")
	public ResponseEntity<List<StocksAndSupplierStockOfferDTO>> findStocksAndSupplierStockOfferByName(
			@PathVariable String name) {
		List<Object[]> result = feedstocksService.findStocksAndSupplierStockOfferByName(name);

		List<StocksAndSupplierStockOfferDTO> dtos = new ArrayList<>();
		for (Object[] row : result) {
			Stocks stocks = (Stocks) row[0];
			SupplierStockOffer supplierStockOffer = (SupplierStockOffer) row[1];
			dtos.add(new StocksAndSupplierStockOfferDTO(stocks, supplierStockOffer));
		}

		return ResponseEntity.ok(dtos);
	}

	@PostMapping("/create")
	public ResponseEntity<Stocks> createStock(@RequestBody Stocks newStock) {
		Stocks createdStock = feedstocksService.createStock(newStock);
		return ResponseEntity.ok(createdStock);
	}

	@DeleteMapping("/{Id}")
    public void deleteStock(@PathVariable Long stockId) {
        stocksService.deleteStockById(stockId);
    }
}
