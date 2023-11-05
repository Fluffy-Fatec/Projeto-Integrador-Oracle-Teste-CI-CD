package com.fluffy.backend.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fluffy.backend.DTO.SupplierStockOfferDTO;
import com.fluffy.backend.entity.Stocks;
import com.fluffy.backend.entity.SupplierStockOffer;
import com.fluffy.backend.entity.Suppliers;
import com.fluffy.backend.repository.StocksRepository;
import com.fluffy.backend.repository.SupplierStockOfferRepository;
import com.fluffy.backend.repository.SuppliersRepository;
import com.fluffy.backend.service.SupplierStockOfferService;

@SpringBootTest
@AutoConfigureMockMvc
public class SupplierStockOfferControllerTest {

    @MockBean
    private SupplierStockOfferService supplierStockOfferService;
    @MockBean
    private SupplierStockOfferRepository supplierStockOfferRepository;
    @MockBean
    private SuppliersRepository suppliersRepository;
    @MockBean
    private StocksRepository stocksRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    // Testa a criação de uma nova oferta de estoque de fornecedor
    public void testCreateSupplierStockOffer() throws Exception {
        SupplierStockOfferDTO supplierStockOfferDTO = new SupplierStockOfferDTO();
        supplierStockOfferDTO.setSupplierId(1L);
        supplierStockOfferDTO.setStockId(1L);
        supplierStockOfferDTO.setQuantityCan(10.0);
        supplierStockOfferDTO.setMeasurement("unit");
        BigDecimal value = BigDecimal.valueOf(5);
        supplierStockOfferDTO.setValue(value);

        SupplierStockOffer supplierStockOffer = new SupplierStockOffer();
        supplierStockOffer.setIdSfOffer(1L);

        Mockito.when(suppliersRepository.findById(1L)).thenReturn(Optional.of(new Suppliers()));
        Mockito.when(stocksRepository.findById(1L)).thenReturn(Optional.of(new Stocks()));
        Mockito.when(supplierStockOfferRepository.save(Mockito.any(SupplierStockOffer.class))).thenReturn(supplierStockOffer);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRequest = objectMapper.writeValueAsString(supplierStockOfferDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/supplier-stock-offers/create")
                .content(jsonRequest)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    // Testa a recuperação de uma oferta de estoque de fornecedor existente

    public void testGetSupplierStockOffer() throws Exception {
        SupplierStockOffer supplierStockOffer = new SupplierStockOffer();
        supplierStockOffer.setIdSfOffer(1L);

        Mockito.when(supplierStockOfferRepository.findById(1L)).thenReturn(Optional.of(supplierStockOffer));

        mockMvc.perform(MockMvcRequestBuilders.get("/supplier-stock-offers/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.idSfOffer").value(1));
    }

    @Test
    // Testa a atualização de uma oferta de estoque de fornecedor existente

    public void testUpdateSupplierStockOffer() throws Exception {
        SupplierStockOfferDTO supplierStockOfferDTO = new SupplierStockOfferDTO();
        supplierStockOfferDTO.setSupplierId(1L);
        supplierStockOfferDTO.setStockId(1L);
        supplierStockOfferDTO.setQuantityCan(20.0);
        supplierStockOfferDTO.setMeasurement("unit");
        BigDecimal value = BigDecimal.valueOf(5);
        supplierStockOfferDTO.setValue(value);

        SupplierStockOffer existingSupplierStockOffer = new SupplierStockOffer();
        existingSupplierStockOffer.setIdSfOffer(1L);

        Mockito.when(supplierStockOfferRepository.findById(1L)).thenReturn(Optional.of(existingSupplierStockOffer));
        Mockito.when(suppliersRepository.findById(1L)).thenReturn(Optional.of(new Suppliers()));
        Mockito.when(stocksRepository.findById(1L)).thenReturn(Optional.of(new Stocks()));
        Mockito.when(supplierStockOfferRepository.save(Mockito.any(SupplierStockOffer.class))).thenReturn(existingSupplierStockOffer);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRequest = objectMapper.writeValueAsString(supplierStockOfferDTO);

        mockMvc.perform(MockMvcRequestBuilders.put("/supplier-stock-offers/1")
                .content(jsonRequest)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    // Testa a listagem de todas as ofertas de estoque de fornecedor
    public void testListAllSupplierStockOffer() throws Exception {
        List<SupplierStockOffer> supplierStockOfferList = Collections.singletonList(new SupplierStockOffer());
        Mockito.when(supplierStockOfferService.getAllSupplierStockOffer()).thenReturn(supplierStockOfferList);

        mockMvc.perform(MockMvcRequestBuilders.get("/supplier-stock-offers"))
                .andExpect(status().isOk());
    }


    @Test
    // Testa a exclusão de uma oferta de estoque de fornecedor

    public void testDeleteSupplierStockOffer() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/supplier-stock-offers/1"))
                .andExpect(status().isOk());
    }
}
