package com.example.groww.Portfolio_Application.impl;

import com.example.groww.Portfolio_Application.dto.HoldingDTO;
import com.example.groww.Portfolio_Application.dto.UserDTO;
import com.example.groww.Portfolio_Application.entity.Holdings;
import com.example.groww.Portfolio_Application.entity.Stocks;
import com.example.groww.Portfolio_Application.repository.HoldingRepository;
import com.example.groww.Portfolio_Application.repository.StockRepository;
import com.example.groww.Portfolio_Application.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class UserServiceImplTest {

    @Mock
    private HoldingRepository holdingRepository;

    @Mock
    private StockRepository stocksRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void testGetHoldingDTO() {
        Holdings mockHoldings = new Holdings();
        mockHoldings.setStockId("AAPL");
        mockHoldings.setQuantity(10);
        mockHoldings.setTotalBuyPrice(5000.0f);
        mockHoldings.setReturns(100.0f);
        Stocks mockStock = new Stocks();
        mockStock.setName("Apple Inc.");
        mockStock.setLastPrice(150.0f);
        when(stocksRepository.findById("AAPL")).thenReturn(Optional.of(mockStock));
        HoldingDTO result = userService.getHoldingDTO(mockHoldings);
        assertEquals("AAPL", result.getStockId());
        assertEquals("Apple Inc.", result.getStockName());
        assertEquals(10, result.getQuantity());
        assertEquals(500.0f, result.getBuyPrice());
        assertEquals(150.0f, result.getCurrentPrice());
        assertEquals(100.0f, result.getReturns());
    }

    @Test
    void testGetUserInfo() {
        long userId = 123;
        Holdings mockHoldings = new Holdings();
        mockHoldings.setStockId("AAPL");
        mockHoldings.setQuantity(10);
        mockHoldings.setTotalBuyPrice(5000.0f);
        mockHoldings.setReturns(100.0f);
        when(holdingRepository.findAllByUserId(userId)).thenReturn(Collections.singletonList(mockHoldings));
        when(stocksRepository.findById("AAPL")).thenReturn(Optional.of(new Stocks()));

        UserDTO result = userService.getUserInfo(userId);
        assertEquals(1, result.getHoldingDTO().size());
        assertEquals(500.0f, result.getTotalProtfolioHolding());
        assertEquals(500.0f, result.getTotalBuyPrice());
        assertEquals(100.0f, result.getTotalReturns());
        assertEquals(1.0f, result.getTotalReturnsPercentage());
    }
}

