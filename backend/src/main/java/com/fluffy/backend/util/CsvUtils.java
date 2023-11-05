package com.fluffy.backend.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fluffy.backend.DTO.CsvData;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class CsvUtils {
	public static List<CsvData> readCsv(MultipartFile file)
			throws IOException, CsvValidationException, NumberFormatException {
		List<CsvData> csvDataList = new ArrayList<>();

		try (InputStream is = file.getInputStream(); CSVReader reader = new CSVReader(new InputStreamReader(is))) {

			String[] line;
			reader.readNext();

			while ((line = reader.readNext()) != null) {
				CsvData csvData = new CsvData();
				csvData.setProductName(line[0]);
				csvData.setProductValue(new BigDecimal(line[1]));
				csvData.setProductType(line[2]);
				csvData.setStatus(line[3]);
				csvData.setPcQuantity(new BigDecimal(line[4]));
				csvData.setPcMeasurement(line[5]);
				csvData.setCommandNumber(Integer.parseInt(line[6]));
				csvData.setPcDatetimeOrder(Timestamp.valueOf(line[7]));
				csvData.setCommandDateTime(Timestamp.valueOf(line[8]));
				csvData.setCommandValue(new BigDecimal(line[9]));
				csvDataList.add(csvData);
			}
		}

		return csvDataList;
	}
}
