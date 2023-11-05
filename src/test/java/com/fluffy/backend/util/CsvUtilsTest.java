package com.fluffy.backend.util;

import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.fluffy.backend.DTO.CsvData;
import com.opencsv.exceptions.CsvValidationException;

public class CsvUtilsTest {

	private CsvUtils csvUtils;

	@BeforeEach
	public void setUp() {
		csvUtils = new CsvUtils();
	}

	public void testReadCsv() throws IOException, CsvValidationException {
		String csvContent = "Product Name,Product Value,Product Type,Status,PC Quantity,PC Measurement,PC Turn,Command Number,PC Datetime Order\n"
				+ "Product 1,10.0,Type A,Ativo,5.0,cm,1,123,2023-09-18T10:00:00\n"
				+ "Product 2,20.0,Type B,Ativo,8.0,cm,2,456,2023-09-18T12:00:00\n";
		InputStream inputStream = IOUtils.toInputStream(csvContent, StandardCharsets.UTF_8);
		MultipartFile multipartFile = new MockMultipartFile("test.csv", inputStream);

		CsvUtils csvUtils = new CsvUtils();

		List<CsvData> csvDataList = csvUtils.readCsv(multipartFile);

		Assertions.assertEquals(2, csvDataList.size());

		CsvData csvData1 = csvDataList.get(0);
		Assertions.assertEquals("Product 1", csvData1.getProductName());
		Assertions.assertEquals(new BigDecimal("10.0"), csvData1.getProductValue());
		Assertions.assertEquals("Type A", csvData1.getProductType());
		Assertions.assertEquals("Ativo", csvData1.getProductType());
		Assertions.assertEquals(new BigDecimal("5.0"), csvData1.getPcQuantity());
		Assertions.assertEquals("cm", csvData1.getPcMeasurement());
		Assertions.assertEquals(123, csvData1.getCommandNumber());
		Assertions.assertEquals(Timestamp.valueOf("2023-09-18 10:00:00"), // Assegure-se de que o formato corresponda ao
																			// CSV
				csvData1.getPcDatetimeOrder());
		Assertions.assertEquals(Timestamp.valueOf("2023-09-18 10:00:00"), // Assegure-se de que o formato corresponda ao
																			// CSV
				csvData1.getCommandDateTime());
		Assertions.assertEquals(new BigDecimal("50.0"), csvData1.getCommandValue());

		CsvData csvData2 = csvDataList.get(1);
		Assertions.assertEquals("Product 2", csvData2.getProductName());
		Assertions.assertEquals(new BigDecimal("20.0"), csvData2.getProductValue());
		Assertions.assertEquals("Type B", csvData2.getProductType());
		Assertions.assertEquals("Ativo", csvData1.getProductType());
		Assertions.assertEquals(new BigDecimal("8.0"), csvData2.getPcQuantity());
		Assertions.assertEquals("cm", csvData2.getPcMeasurement());
		Assertions.assertEquals(456, csvData2.getCommandNumber());
		Assertions.assertEquals(Timestamp.valueOf("2023-09-18 12:00:00"), csvData2.getPcDatetimeOrder());
		Assertions.assertEquals(Timestamp.valueOf("2023-09-18 12:00:00"), csvData2.getCommandDateTime());
		Assertions.assertEquals(new BigDecimal("3.0"), csvData2.getCommandValue());
	}

	@Test
	public void testReadCsvWithIOException() throws IOException, CsvValidationException {
		MultipartFile multipartFile = Mockito.mock(MultipartFile.class);
		when(multipartFile.getInputStream()).thenThrow(new IOException());

		Assertions.assertThrows(IOException.class, () -> csvUtils.readCsv(multipartFile));
	}
}
