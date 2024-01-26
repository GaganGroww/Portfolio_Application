package com.example.groww.Portfolio_Application.controller;

import com.example.groww.Portfolio_Application.controller.StockController;
import com.example.groww.Portfolio_Application.dto.StockDTO;
import com.example.groww.Portfolio_Application.service.StockService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;

import static org.mockito.Mockito.when;

class StockControllerTest {

    @Mock
    private StockService stockService;

    @InjectMocks
    private StockController stockController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(stockController).build();
    }

    @Test
    void testGetStock() throws Exception {
        String stockId = "ABC123";
        StockDTO mockStockDTO = new StockDTO();
        mockStockDTO.setId(stockId);

        when(stockService.getStock(stockId)).thenReturn(mockStockDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/Stock/{stockId}", stockId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(stockId));
    }

    @Test
    void testHandleCsvUpload() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "test.csv", MediaType.TEXT_PLAIN_VALUE, "CSV data".getBytes());

        when(stockService.getcsv(file)).thenReturn("Successfully added data from CSV");

        mockMvc.perform(MockMvcRequestBuilders.multipart("/Stock/update").file(file))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Successfully added data from CSV"));
    }
}

