package com.fluffy.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fluffy.backend.DTO.CsvData;
import com.fluffy.backend.entity.Command;
import com.fluffy.backend.entity.Product;
import com.fluffy.backend.entity.ProductCommand;
import com.fluffy.backend.repository.CommandRepository;
import com.fluffy.backend.repository.ProductCommandRepository;
import com.fluffy.backend.repository.ProductRepository;

import java.util.List; // Import the correct List class

@Service
public class CsvService {
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CommandRepository commandRepository;

	@Autowired
	private ProductCommandRepository productCommandRepository;

	public void processCsvData(List<CsvData> csvDataList) {
		for (CsvData csvData : csvDataList) {

			Product product = new Product();
			product.setName(csvData.getProductName());
			product.setValue(csvData.getProductValue());
			product.setType(csvData.getProductType());
			product.setStatus(csvData.getStatus());
			productRepository.saveAndFlush(product);

			Command command = new Command();
			command.setCommandNumber(csvData.getCommandNumber());
			command.setCommandDateTime(csvData.getCommandDateTime());
			command.setCommandValue(csvData.getCommandValue());
			commandRepository.saveAndFlush(command);

			ProductCommand productCommand = new ProductCommand();
			productCommand.setProduct(product);
			productCommand.setCommand(command);
			productCommand.setPcQuantity(csvData.getPcQuantity());
			productCommand.setPcMeasurement(csvData.getPcMeasurement());
			//productCommand.set(csvData.getPcTurn());
			productCommand.setPcDatetimeOrder(csvData.getPcDatetimeOrder());
			productCommandRepository.saveAndFlush(productCommand);
		}
	}
}
