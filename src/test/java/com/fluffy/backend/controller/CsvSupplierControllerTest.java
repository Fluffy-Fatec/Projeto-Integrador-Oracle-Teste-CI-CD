package com.fluffy.backend.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.fluffy.backend.service.CsvSupplierService;

public class CsvSupplierControllerTest {
	@InjectMocks
	private CsvSupplierController csvSupplierController;

	@Mock
	private CsvSupplierService csvService;

	@SuppressWarnings("deprecation")
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testUploadCsvSuccess() throws IOException {
	    String csvContent = "Name,Segment,DeliveryForecast,Cnpj,Phone,Address,City,State,Status,PaymentMethodName,PaymentMethodPayDay,FeedName,AmountAvailable,FeedMeasurement,QuantityCan,Measurement,Value\n"
	            + "Company A,Segment A,2023-10-14,1234567890,1234567890,Address A,City A,State A,1,PaymentMethodA,15,FeedA,100.0,kg,50.0,liters,500.50\n";
	    MultipartFile mockFile = new MockMultipartFile("file", "test.csv", "text/csv", csvContent.getBytes());

	    ResponseEntity<String> response = csvSupplierController.uploadCsv(mockFile);

	    assertEquals(HttpStatus.OK, response.getStatusCode());
	    assertEquals("Dados CSV importados com sucesso.", response.getBody());
	}


	@Test
	public void testUploadCsvEmptyFile() throws IOException {
		MultipartFile emptyFile = new MockMultipartFile("test.csv", new byte[0]);

		ResponseEntity<String> response = csvSupplierController.uploadCsv(emptyFile);

		verify(csvService, never()).processCsvData(any());
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals("O arquivo CSV está vazio.", response.getBody());
	}

	@Test
	public void testUploadCsvError() throws IOException {
	
		String csvContent = "Name,Segment\n"; 
		MultipartFile mockFile = new MockMultipartFile("file", "test.csv", "text/csv", csvContent.getBytes());

		try {
			csvSupplierController.uploadCsv(mockFile);
		} catch (Exception e) {
			assertTrue(e.getMessage().contains("Erro ao importar dados CSV"));
		}
	}
}