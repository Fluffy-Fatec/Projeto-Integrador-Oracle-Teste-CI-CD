package com.fluffy.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fluffy.backend.DTO.StocksListDTO;
import com.fluffy.backend.entity.Stocks;
import com.fluffy.backend.entity.SupplierStock;
import com.fluffy.backend.entity.SupplierStockOffer;
import com.fluffy.backend.repository.StocksRepository;
import com.fluffy.backend.repository.SupplierStockOfferRepository;
import com.fluffy.backend.repository.SupplierStockRepository;

@Service
public class StocksService {

	@Autowired
	StocksRepository feedstocksRepository;

	@Autowired
	SupplierStockRepository supplierFeedstockRepository;

	@Autowired
	SupplierStockOfferRepository supplierStockOfferRepository;

	public List<StocksListDTO> getAllFeedstocks() {

		List<StocksListDTO> feedstocksListDTO = new ArrayList<>();

		for (Stocks feedstock : feedstocksRepository.findAll()) {
			StocksListDTO feedstocksDTO = new StocksListDTO();

			SupplierStock supplierFeedstock = supplierFeedstockRepository.findByStocks(feedstock);

			feedstocksDTO.setIdFeedstock(feedstock.getIdstock());
			feedstocksDTO.setName(feedstock.getName());
			feedstocksDTO.setAmountAvailable(feedstock.getAmountAvailable());
			feedstocksDTO.setMeasurement(feedstock.getMeasurement());
			feedstocksDTO.setPrice(supplierFeedstock.getPrice());
			feedstocksListDTO.add(feedstocksDTO);
		}
		return feedstocksListDTO;
	}

	public List<SupplierStockOffer> getAllSupplierStockOffer() {
		return supplierStockOfferRepository.findAll();
	}

	public List<Object[]> findStocksAndSupplierStockOfferByName(String name) {
		return feedstocksRepository.findStocksAndSupplierStockOfferByName(name);
	}

	public Stocks createStock(Stocks newStock) {
		return feedstocksRepository.save(newStock);
	}

	public void deleteStockById(Long stockId) {
		feedstocksRepository.deleteById(stockId);
	}
	public List<Stocks> getAllStocks() {
        return feedstocksRepository.findAll();
    }
}






