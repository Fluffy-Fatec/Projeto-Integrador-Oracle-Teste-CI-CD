package com.fluffy.backend.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.fluffy.backend.service.CsvService;
import com.opencsv.exceptions.CsvValidationException;

public class CsvControllerTest {

    @InjectMocks
    private CsvController csvController;

    @Mock
    private CsvService csvService;

    @SuppressWarnings("deprecation")
	@BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testUploadCsvSuccess() throws IOException, CsvValidationException {
        // Create a MockMultipartFile with file name, field name, and content
        String csvContent = "Product Name,Product Value,Product Type,PC Quantity,PC Measurement,Command Number,PC Datetime Order,Datetime Command,Command Value\n"
                + "Product 1,10.0,Type A,5.0,cm,123,2023-09-18 10:00:00,2023-09-18 11:00:00,12.3\n";
        MultipartFile mockFile = new MockMultipartFile("file", "test.csv", "text/csv", csvContent.getBytes());

        ResponseEntity<String> response = csvController.uploadCsv(mockFile);

        // Verify that the response is OK
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Dados CSV importados com sucesso.", response.getBody());
    }

    @Test
    public void testUploadCsvEmptyFile() throws IOException {
        MultipartFile emptyFile = new MockMultipartFile("test.csv", new byte[0]);

        ResponseEntity<String> response = csvController.uploadCsv(emptyFile);

        verify(csvService, never()).processCsvData(any());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("O arquivo CSV está vazio.", response.getBody());
    }

    @Test
    public void testUploadCsvError() throws IOException, CsvValidationException {
        // Create a MockMultipartFile with a valid file name and invalid CSV content
        String csvContent = "Product Name,Product Value,Product Type\n" // Invalid CSV format
                + "Product 1,10.0,Type A\n";
        MultipartFile mockFile = new MockMultipartFile("file", "test.csv", "text/csv", csvContent.getBytes());

        ResponseEntity<String> response = csvController.uploadCsv(mockFile);

        // Verify that the response is a 500 error and contains the expected exception message
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertTrue(response.getBody().contains("Erro ao importar dados CSV"));
    }
}
