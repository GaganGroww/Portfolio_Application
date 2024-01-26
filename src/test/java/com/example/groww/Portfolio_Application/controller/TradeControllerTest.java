package com.example.groww.Portfolio_Application.controller;
import com.example.groww.Portfolio_Application.dto.TradeDTO;
import com.example.groww.Portfolio_Application.service.TradeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;

class TradeControllerTest {

    @Mock
    private TradeService tradeService;

    @InjectMocks
    private TradeController tradeController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(tradeController).build();
    }

    @Test
    void testAddTrade() throws Exception {
        // Mock data
        TradeDTO mockTradeDTO = new TradeDTO();
        mockTradeDTO.setUserId(123);
        mockTradeDTO.setTradeType("BUY");
        mockTradeDTO.setQuantity(50);
        mockTradeDTO.setStockId("XYZ");

        // Mock the service behavior
        String expectedResult = "Trade added successfully";
        when(tradeService.convertToTradedto(Mockito.any(TradeDTO.class))).thenReturn(expectedResult);

        // Perform the HTTP request and validate the response
        mockMvc.perform(MockMvcRequestBuilders.post("/Trade/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userId\":123,\"tradeType\":\"BUY\",\"quantity\":50,\"stockId\":\"XYZ\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expectedResult));
    }
}


