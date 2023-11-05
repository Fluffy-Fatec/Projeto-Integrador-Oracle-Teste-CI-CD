package com.fluffy.backend.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;

import com.fluffy.backend.DTO.CsvSupplerData;

public class CsvSupplierUtilsTest {
    @InjectMocks
    private CsvSupplierUtils csvSupplierUtils;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testReadCsv() throws IOException {
        String csvData = "Name,Segment,DeliveryForecast,Cnpj,Phone,Address,City,State,Status,PaymentMethodName,PaymentMethodPayDay,FeedName,AmountAvailable,FeedMeasurement,QuantityCan,Measurement,Value\n" +
                "Company A,Segment A,2023-10-14,1234567890,1234567890,Address A,City A,State A,1,PaymentMethodA,20231014,FeedA,100.0,kg,50.0,liters,500.50\n" +
                "Company B,Segment B,2023-10-15,0987654321,0987654321,Address B,City B,State B,1,PaymentMethodB,20231015,FeedB,200.0,liters,75.0,kg,750.75";

        MockMultipartFile csvFile = new MockMultipartFile("file", "test.csv", "text/csv", csvData.getBytes());

        try {
            List<CsvSupplerData> csvDataList = csvSupplierUtils.readCsv(csvFile);

            assertEquals(2, csvDataList.size());

            CsvSupplerData data1 = csvDataList.get(0);
            assertEquals("Company A", data1.getName());
            assertEquals("Segment A", data1.getSegment());
            assertEquals("2023-10-14", data1.getDeliveryForecast());
            assertEquals("1234567890", data1.getCnpj());
            assertEquals(1234567890, data1.getPhone());
            assertEquals("Address A", data1.getAddress());
            assertEquals("City A", data1.getCity());
            assertEquals("State A", data1.getState());
            assertEquals(1, data1.getStatus());
            assertEquals("PaymentMethodA", data1.getPaymentMethodName());
            assertEquals(20231014, data1.getPaymentMethodPayDay());
            assertEquals("FeedA", data1.getFeedName());
            assertEquals(100.0, data1.getAmountAvailable(), 0.001);
            assertEquals("kg", data1.getFeedMeasurement());
            assertEquals(50.0, data1.getQuantityCan(), 0.001);
            assertEquals("liters", data1.getMeasurement());
            assertEquals(new BigDecimal("500.50"), data1.getValue());

            CsvSupplerData data2 = csvDataList.get(1);
            assertEquals("Company B", data2.getName());
            // Adicione as verificações apropriadas para os campos do data2, se necessário.
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
