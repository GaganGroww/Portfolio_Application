package com.example.groww.Portfolio_Application.impl;

import com.example.groww.Portfolio_Application.dto.TradeDTO;
import com.example.groww.Portfolio_Application.entity.HoldingsId;
import com.example.groww.Portfolio_Application.entity.Stocks;
import com.example.groww.Portfolio_Application.entity.Trade;
import com.example.groww.Portfolio_Application.entity.User;
import com.example.groww.Portfolio_Application.repository.HoldingRepository;
import com.example.groww.Portfolio_Application.repository.StockRepository;
import com.example.groww.Portfolio_Application.repository.TradeRepository;
import com.example.groww.Portfolio_Application.repository.UserRepository;
import com.example.groww.Portfolio_Application.service.impl.TradeServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class TradeServiceImplTest {

    @Mock
    private TradeRepository tradeRepository;

    @Mock
    private StockRepository stocksRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private HoldingRepository holdingRepository;

    @InjectMocks
    private TradeServiceImpl tradeService;

    @Test
    void testAddTrade() {
        // Mock data
        Trade mockTrade = new Trade();
        mockTrade.setUserId(1L);
        mockTrade.setStockId("AAPL");
        mockTrade.setQuantity(10);
        mockTrade.setTradeType(Trade.TradeType.BUY);

        Stocks mockStock = new Stocks();
        mockStock.setId("AAPL");
        mockStock.setOpenPrice(150.0f);
        mockStock.setLastPrice(160.0f);

        when(stocksRepository.findById("AAPL")).thenReturn(Optional.of(mockStock));
        when(userRepository.findById(1L)).thenReturn(Optional.of(new User()));
        when(holdingRepository.findById(new HoldingsId(1L, "AAPL"))).thenReturn(Optional.empty());

        // Call the method
        String result = tradeService.addTrade(mockTrade);

        // Assert the result
        assertEquals("Successful, Data inserted Successfully", result);
    }

    @Test
    void testConvertToTradedto() {
        // Mock data
        TradeDTO mockTradeDTO = new TradeDTO();
        mockTradeDTO.setUserId(1L);
        mockTradeDTO.setStockId("AAPL");
        mockTradeDTO.setQuantity(10);
        mockTradeDTO.setTradeType("BUY");

        // Mock the service behavior
        when(tradeRepository.save(Mockito.any(Trade.class))).thenReturn(new Trade());

        // Call the method
        String result = tradeService.convertToTradedto(mockTradeDTO);

        // Assert the result
        assertEquals("Successful, Data inserted Successfully", result);
    }
}

