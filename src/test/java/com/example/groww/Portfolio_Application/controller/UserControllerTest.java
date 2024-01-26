package com.example.groww.Portfolio_Application.impl;

import com.example.groww.Portfolio_Application.controller.UserController;
import com.example.groww.Portfolio_Application.dto.HoldingDTO;
import com.example.groww.Portfolio_Application.dto.UserDTO;
import com.example.groww.Portfolio_Application.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void testGetUser() throws Exception {
        // Mock data
        long userId = 123;
        UserDTO mockUserDTO = new UserDTO();
        HoldingDTO mockholdingDTO = new HoldingDTO();

        mockholdingDTO.setStockId("XYZ");
        mockholdingDTO.setQuantity(10);
        mockholdingDTO.setCurrentPrice(11);
        mockholdingDTO.setStockName("XYZ");
        mockholdingDTO.setBuyPrice(9);
        mockholdingDTO.setReturns(5);

        mockUserDTO.setHoldingDTO(Collections.singletonList(mockholdingDTO));
        mockUserDTO.setTotalProtfolioHolding(5000.0f);
        mockUserDTO.setTotalBuyPrice(4500.0f);
        mockUserDTO.setTotalReturns(500.0f);
        mockUserDTO.setTotalReturnsPercentage(11.11f);

        // Mock the service behavior
        when(userService.getUserInfo(userId)).thenReturn(mockUserDTO);

        // Perform the HTTP request and validate the response
        mockMvc.perform(MockMvcRequestBuilders.get("/User/{userId}", userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.holdingDTO[0].stockId").value("XYZ"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.holdingDTO[0].quantity").value(10))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalProtfolioHolding").value(5000.0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalBuyPrice").value(4500.0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalReturns").value(500.0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalReturnsPercentage").value(11.11));
    }
}