package com.fluffy.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fluffy.backend.DTO.SupplierDTO;
import com.fluffy.backend.entity.Suppliers;
import com.fluffy.backend.service.SuppliersService;

@CrossOrigin
@RestController
@RequestMapping("/api/suppliers")
public class SuppliersController {

	@Autowired SuppliersService suppliersService;

	@PostMapping
	public Suppliers createSupplierAndPayment(@RequestBody SupplierDTO supplierDTO) {
		return suppliersService.createSupplierAndPayment(supplierDTO);
	}

	@GetMapping
	public List<Suppliers> listAllSuppliers() {
		return suppliersService.getAllSuppliers();
	}

	@GetMapping("/{name}")
	public List<Suppliers> listSuppliersByName(@PathVariable String name) {
		return suppliersService.listSuppliersByName(name);
	}

	@DeleteMapping("/{id}")
	public void deleteSupplier(@PathVariable Long id) {
		suppliersService.deleteSuppliersPorId(id);
	}

	@PutMapping("/update/{name}")
	public ResponseEntity<Suppliers> updateSupplierAndPaymentByName(@PathVariable String name,
			@RequestBody SupplierDTO updatedSupplier) {
		Suppliers updated = suppliersService.updateSupplierAndPaymentByName(name, updatedSupplier);

		return ResponseEntity.ok(updated);
	}

}
