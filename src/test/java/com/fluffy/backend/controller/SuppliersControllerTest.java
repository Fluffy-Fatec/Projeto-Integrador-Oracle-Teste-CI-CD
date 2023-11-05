package com.fluffy.backend.controller;

import com.fluffy.backend.DTO.SupplierDTO;
import com.fluffy.backend.entity.Suppliers;
import com.fluffy.backend.service.SuppliersService;
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

public class SuppliersControllerTest {

    @Mock
    private SuppliersService suppliersService;

    private SuppliersController suppliersController;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        suppliersController = new SuppliersController();
        suppliersController.suppliersService = suppliersService;
    }

    @Test
    public void testCreateSupplierAndPayment() {
        SupplierDTO supplierDTO = new SupplierDTO();
        Suppliers createdSupplier = new Suppliers();

        Mockito.when(suppliersService.createSupplierAndPayment(any(SupplierDTO.class))).thenReturn(createdSupplier);

        Suppliers result = suppliersController.createSupplierAndPayment(supplierDTO);

        assertEquals(createdSupplier, result);
    }

    @Test
    public void testListAllSuppliers() {
        List<Suppliers> suppliersList = new ArrayList<>();
        Mockito.when(suppliersService.getAllSuppliers()).thenReturn(suppliersList);

        List<Suppliers> result = suppliersController.listAllSuppliers();

        assertEquals(suppliersList, result);
    }

    @Test
    public void testListSuppliersByName() {
        String name = "TestSupplier";
        List<Suppliers> suppliersList = new ArrayList<>();

        Mockito.when(suppliersService.listSuppliersByName(name)).thenReturn(suppliersList);

        List<Suppliers> result = suppliersController.listSuppliersByName(name);

        assertEquals(suppliersList, result);
    }

    @Test
    public void testDeleteSupplier() {
        Long supplierId = 1L;

        suppliersController.deleteSupplier(supplierId);

        Mockito.verify(suppliersService).deleteSuppliersPorId(supplierId);
    }

    @Test
    public void testUpdateSupplierAndPaymentByName() {
        String name = "TestSupplier";
        SupplierDTO updatedSupplier = new SupplierDTO();
        Suppliers updated = new Suppliers();

        Mockito.when(suppliersService.updateSupplierAndPaymentByName(eq(name), any(SupplierDTO.class))).thenReturn(updated);

        ResponseEntity<Suppliers> result = suppliersController.updateSupplierAndPaymentByName(name, updatedSupplier);

        assertEquals(updated, result.getBody());
    }
}
