package com.fluffy.backend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.fluffy.backend.DTO.SupplierDTO;
import com.fluffy.backend.entity.PaymentsMethods;
import com.fluffy.backend.entity.Suppliers;
import com.fluffy.backend.repository.PaymentsMethodsRepository;
import com.fluffy.backend.repository.SuppliersRepository;

@SpringBootTest
public class SuppliersServiceTest {
    @InjectMocks
    private SuppliersService suppliersService;

    @Mock
    private SuppliersRepository suppliersRepository;

    @Mock
    private PaymentsMethodsRepository paymentsMethodsRepository;

    @Test
    public void testCreateSupplierAndPayment() {
        SupplierDTO supplierDTO = new SupplierDTO();
        supplierDTO.setName("SupplierName");
        supplierDTO.setSegment("SegmentName");
        supplierDTO.setDeliveryForecast("2023-10-14");
        supplierDTO.setCnpj("1234567890");
        supplierDTO.setPhone((long) 1234567890);
        supplierDTO.setAddress("AddressName");
        supplierDTO.setCity("CityName");
        supplierDTO.setState("1");
        supplierDTO.setStatus(1);
        supplierDTO.setPaymentMethodName("PaymentMethod");
        supplierDTO.setPaymentMethodPayDay(Integer.parseInt("1"));

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

        when(suppliersRepository.save(supplier)).thenReturn(supplier);
        when(paymentsMethodsRepository.save(paymentMethod)).thenReturn(paymentMethod);

        Suppliers createdSupplier = suppliersService.createSupplierAndPayment(supplierDTO);

        assertNotNull(createdSupplier);
        assertEquals(supplierDTO.getName(), createdSupplier.getName());
        assertEquals(supplierDTO.getSegment(), createdSupplier.getSegment());
        assertEquals(supplierDTO.getPaymentMethodName(), createdSupplier.getPaymentsMethods().getName());
    }

    @Test
    public void testGetAllSuppliers() {
        List<Suppliers> suppliersList = new ArrayList<>();

        when(suppliersRepository.findAll()).thenReturn(suppliersList);

        List<Suppliers> result = suppliersService.getAllSuppliers();

        assertNotNull(result);
        assertEquals(suppliersList.size(), result.size());
    }

    @Test
    public void testListSuppliersByName() {
        String name = "SupplierName";
        List<Suppliers> suppliersList = new ArrayList<>();

        when(suppliersRepository.findByName(name)).thenReturn(suppliersList);

        List<Suppliers> result = suppliersService.listSuppliersByName(name);

        assertNotNull(result);
        assertEquals(suppliersList.size(), result.size());
    }

    @Test
    public void testDeleteSuppliersPorId() {
        Long id = 1L;

        when(suppliersRepository.findById(id)).thenReturn(Optional.of(new Suppliers()));

        suppliersService.deleteSuppliersPorId(id);

        verify(suppliersRepository, times(1)).delete(any(Suppliers.class));
    }

    @Test
    public void testUpdateSupplierAndPaymentByName() {
        String name = "SupplierName";
        SupplierDTO updatedSupplier = new SupplierDTO();
        updatedSupplier.setName("UpdatedName");
        updatedSupplier.setSegment("UpdatedSegment");
        updatedSupplier.setDeliveryForecast("2023-10-15");
        updatedSupplier.setCnpj("9876543210");
        updatedSupplier.setPhone((long) 1234567890);
        updatedSupplier.setAddress("UpdatedAddress");
        updatedSupplier.setCity("UpdatedCity");
        updatedSupplier.setState("UpdatedState");
        updatedSupplier.setStatus(1);
        updatedSupplier.setPaymentMethodName("UpdatedPaymentMethod");
        updatedSupplier.setPaymentMethodPayDay(Integer.parseInt("1"));

        Suppliers existingSupplier = new Suppliers();
        existingSupplier.setName("SupplierName");
        existingSupplier.setSegment("SegmentName");
        existingSupplier.setDeliveryForecast("2023-10-14");
        existingSupplier.setCnpj("1234567890");
        existingSupplier.setPhone((long) 1234567890);
        existingSupplier.setAddress("AddressName");
        existingSupplier.setCity("CityName");
        existingSupplier.setState("StateName");
        existingSupplier.setStatus(1);

        PaymentsMethods paymentMethod = new PaymentsMethods();
        paymentMethod.setName("PaymentMethod");
        paymentMethod.setPayDay(Integer.parseInt("1"));

        existingSupplier.setPaymentsMethods(paymentMethod);

        when(suppliersRepository.findBySupplierName(name)).thenReturn(existingSupplier);
        when(suppliersRepository.save(existingSupplier)).thenReturn(existingSupplier);

        Suppliers result = suppliersService.updateSupplierAndPaymentByName(name, updatedSupplier);

        assertNotNull(result);
        assertEquals(updatedSupplier.getName(), result.getName());
        assertEquals(updatedSupplier.getSegment(), result.getSegment());
        assertEquals(updatedSupplier.getPaymentMethodName(), result.getPaymentsMethods().getName());
    }
    @Test
    public void testUpdateSupplierAndPaymentByName_WhenSupplierNotFound() {
        String name = "NonExistentSupplierName";
        SupplierDTO updatedSupplier = new SupplierDTO();

        when(suppliersRepository.findBySupplierName(name)).thenReturn(null);

        Suppliers result = suppliersService.updateSupplierAndPaymentByName(name, updatedSupplier);

        assertNull(result); 
    }

}
