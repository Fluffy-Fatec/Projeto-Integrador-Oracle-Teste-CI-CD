package com.fluffy.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fluffy.backend.DTO.SupplierDTO;
import com.fluffy.backend.entity.PaymentsMethods;
import com.fluffy.backend.entity.Suppliers;
import com.fluffy.backend.repository.SuppliersRepository;

@Service
public class SuppliersService {

	@Autowired
	private SuppliersRepository suppliersRepository;

	public Suppliers createSupplierAndPayment(SupplierDTO supplierDTO) {
		Suppliers supplier = new Suppliers();
		supplier.setName(supplierDTO.getName());
		supplier.setSegment(supplierDTO.getSegment());
		supplier.setDeliveryForecast(supplierDTO.getDeliveryForecast());
		supplier.setCnpj(supplierDTO.getCnpj());
		supplier.setPhone(supplierDTO.getPhone());
		supplier.setAddress(supplierDTO.getAddress());
		supplier.setCity(supplierDTO.getCity());
		supplier.setState(supplierDTO.getState());
		supplier.setStatus(supplierDTO.getStatus());

		PaymentsMethods paymentMethod = new PaymentsMethods();
		paymentMethod.setName(supplierDTO.getPaymentMethodName());
		paymentMethod.setPayDay(supplierDTO.getPaymentMethodPayDay());

		supplier.setPaymentsMethods(paymentMethod);

		suppliersRepository.save(supplier);

		return supplier;
	}
	
	  public List<Suppliers> getAllSuppliers() {
	        return suppliersRepository.findAll();
	    }

	public List<Suppliers> listSuppliersByName(String name) {
		return suppliersRepository.findByName(name);
	}

	public void deleteSuppliersPorId(Long id) {
		Suppliers suppliers = suppliersRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Supplier not found"));
		suppliersRepository.delete(suppliers);
	}

	public Suppliers updateSupplierAndPaymentByName(String name, SupplierDTO updatedSupplier) {
		Suppliers existingSupplier = suppliersRepository.findBySupplierName(name);

		if (existingSupplier != null) {
			existingSupplier.setName(updatedSupplier.getName());
			existingSupplier.setSegment(updatedSupplier.getSegment());
			existingSupplier.setDeliveryForecast(updatedSupplier.getDeliveryForecast());
			existingSupplier.setCnpj(updatedSupplier.getCnpj());
			existingSupplier.setPhone(updatedSupplier.getPhone());
			existingSupplier.setAddress(updatedSupplier.getAddress());
			existingSupplier.setCity(updatedSupplier.getCity());
			existingSupplier.setState(updatedSupplier.getState());
			existingSupplier.setStatus(updatedSupplier.getStatus());

			PaymentsMethods paymentMethod = existingSupplier.getPaymentsMethods();
			paymentMethod.setName(updatedSupplier.getPaymentMethodName());
			paymentMethod.setPayDay(updatedSupplier.getPaymentMethodPayDay());

			return suppliersRepository.save(existingSupplier);
		} else {
			return null;
		}
	}
}
