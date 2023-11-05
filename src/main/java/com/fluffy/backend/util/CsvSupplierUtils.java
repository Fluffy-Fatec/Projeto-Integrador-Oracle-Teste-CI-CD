package com.fluffy.backend.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fluffy.backend.DTO.CsvSupplerData;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class CsvSupplierUtils {
	 public static List<CsvSupplerData> readCsv(MultipartFile file)
	            throws IOException, CsvValidationException, NumberFormatException {
	        List<CsvSupplerData> csvDataList = new ArrayList<>();

	        try (InputStream is = file.getInputStream(); CSVReader reader = new CSVReader(new InputStreamReader(is))) {

	            String[] line;
	            reader.readNext();

	            while ((line = reader.readNext()) != null) {
	                CsvSupplerData csvData = new CsvSupplerData();
	                csvData.setName(line[0]);
	                csvData.setSegment(line[1]);
	                csvData.setDeliveryForecast(line[2]);
	                csvData.setCnpj(line[3]);
	                csvData.setPhone(Long.parseLong(line[4]));
	                csvData.setAddress(line[5]);
	                csvData.setCity(line[6]);
	                csvData.setState(line[7]);
	                csvData.setStatus(Integer.parseInt(line[8]));
	                csvData.setPaymentMethodName(line[9]);
	                csvData.setPaymentMethodPayDay(Integer.parseInt(line[10]));
	                csvData.setFeedName(line[11]);
	                csvData.setAmountAvailable(Double.parseDouble(line[12]));
	                csvData.setFeedMeasurement(line[13]);
	                csvData.setQuantityCan(Double.parseDouble(line[14]));
	                csvData.setMeasurement(line[15]);
	                csvData.setValue(new BigDecimal (line[16]));
	                csvDataList.add(csvData);
	            }
	        }

	        return csvDataList;
	    }
}
