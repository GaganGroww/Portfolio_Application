package com.example.groww.Portfolio_Application.service;

import com.example.groww.Portfolio_Application.dto.TradeDTO;
import com.example.groww.Portfolio_Application.entity.Trade;

public interface TradeService {
    String addTrade(Trade trade);
    String convertToTradedto(TradeDTO tradeDTO);
}
