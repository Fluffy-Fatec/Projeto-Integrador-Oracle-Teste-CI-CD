package com.fluffy.backend.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fluffy.backend.DTO.CsvData;
import com.fluffy.backend.entity.Command;
import com.fluffy.backend.entity.Product;
import com.fluffy.backend.entity.ProductCommand;
import com.fluffy.backend.repository.CommandRepository;
import com.fluffy.backend.repository.ProductCommandRepository;
import com.fluffy.backend.repository.ProductRepository;
import com.fluffy.backend.service.CsvService;


public class CsvServiceTest {

    @InjectMocks
    private CsvService csvService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CommandRepository commandRepository;

    @Mock
    private ProductCommandRepository productCommandRepository;

    @SuppressWarnings("deprecation")
	@BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testProcessCsvData() {
    	
        // Criação de um objeto CsvData com dados de exemplo.
        CsvData csvData = new CsvData();
        csvData.setProductName("Pastel");
        csvData.setProductValue(BigDecimal.valueOf(35.0));
        csvData.setProductType("Fritos");
        csvData.setPcQuantity(BigDecimal.valueOf(1.0));
        csvData.setPcMeasurement("Kg");
        csvData.setCommandNumber(123);
        csvData.setPcDatetimeOrder(null);
      

        Product product = new Product();
        product.setName(csvData.getProductName());
        product.setValue(csvData.getProductValue());
        product.setType(csvData.getProductType());

        Command command = new Command();
        command.setCommandNumber(csvData.getCommandNumber());
        csvData.setCommandDateTime(null);
        csvData.setCommandValue(BigDecimal.valueOf(1.0));
        
        // Configuração dos comportamentos esperados
        when(productRepository.saveAndFlush(any(Product.class))).thenReturn(product);
        when(commandRepository.saveAndFlush(any(Command.class))).thenReturn(command);
        when(productCommandRepository.saveAndFlush(any(ProductCommand.class))).thenReturn(new ProductCommand());
       
        // Chamada do método a ser testado.
        csvService.processCsvData(Arrays.asList(csvData));

        // Verifica se os métodos save dos repositórios foram chamados.
        verify(productRepository).saveAndFlush(any(Product.class));
        verify(commandRepository).saveAndFlush(any(Command.class));
        verify(productCommandRepository).saveAndFlush(any(ProductCommand.class));
    }
}
