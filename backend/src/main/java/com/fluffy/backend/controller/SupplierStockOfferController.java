package com.fluffy.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fluffy.backend.DTO.SupplierStockOfferDTO;
import com.fluffy.backend.entity.Stocks;
import com.fluffy.backend.entity.SupplierStockOffer;
import com.fluffy.backend.entity.Suppliers;
import com.fluffy.backend.repository.StocksRepository;
import com.fluffy.backend.repository.SupplierStockOfferRepository;
import com.fluffy.backend.repository.SuppliersRepository;
import com.fluffy.backend.service.SupplierStockOfferService;

@RestController
@RequestMapping("/supplier-stock-offers")
public class SupplierStockOfferController {

	private final SupplierStockOfferService supplierStockOfferService;
	@Autowired
	private SupplierStockOfferRepository supplierStockOfferRepository;

	@Autowired
	private SuppliersRepository suppliersRepository;

	@Autowired
	private StocksRepository stocksRepository;

	@Autowired
	public SupplierStockOfferController(SupplierStockOfferService supplierStockOfferService) {
		this.supplierStockOfferService = supplierStockOfferService;
	}

	@PostMapping("/create")
	public ResponseEntity<SupplierStockOffer> createSupplierStockOffer(
			@RequestBody SupplierStockOfferDTO supplierStockOfferDTO) {
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

		SupplierStockOffer createdSupplierStockOffer = supplierStockOfferRepository.save(newSupplierStockOffer);
		return ResponseEntity.ok(createdSupplierStockOffer);
	}

	@GetMapping("/{id}")
	public ResponseEntity<SupplierStockOffer> getSupplierStockOffer(@PathVariable Long id) {
		SupplierStockOffer supplierStockOffer = supplierStockOfferRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("SupplierStockOffer not found"));
		return ResponseEntity.ok(supplierStockOffer);
	}

	@PutMapping("/{id}")
	public ResponseEntity<SupplierStockOffer> updateSupplierStockOffer(@PathVariable Long id,
			@RequestBody SupplierStockOfferDTO supplierStockOfferDTO) {
		// Verifique se o SupplierStockOffer com o ID fornecido existe
		SupplierStockOffer existingSupplierStockOffer = supplierStockOfferRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("SupplierStockOffer not found"));

		// Atualize os campos do SupplierStockOffer com base nos dados do DTO
		Suppliers supplier = suppliersRepository.findById(supplierStockOfferDTO.getSupplierId())
				.orElseThrow(() -> new IllegalArgumentException("Supplier not found"));
		Stocks stock = stocksRepository.findById(supplierStockOfferDTO.getStockId())
				.orElseThrow(() -> new IllegalArgumentException("Stock not found"));

		existingSupplierStockOffer.setSuppliers(supplier);
		existingSupplierStockOffer.setStocks(stock);
		existingSupplierStockOffer.setQuantityCan(supplierStockOfferDTO.getQuantityCan());
		existingSupplierStockOffer.setMeasurement(supplierStockOfferDTO.getMeasurement());
		existingSupplierStockOffer.setValue(supplierStockOfferDTO.getValue());

		// Salve as atualizações
		SupplierStockOffer updatedSupplierStockOffer = supplierStockOfferRepository.save(existingSupplierStockOffer);

		return ResponseEntity.ok(updatedSupplierStockOffer);
	}

	@GetMapping
	public List<SupplierStockOffer> listAllSupplierStockOffer() {
		return supplierStockOfferService.getAllSupplierStockOffer();
	}

	@DeleteMapping("/{id}")
	public void deleteSupplierStockOffer(@PathVariable Long id) {
		supplierStockOfferService.deleteSupplierStockOfferPorId(id);
	}
}
