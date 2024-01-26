package com.example.groww.Portfolio_Application.dto;

import com.example.groww.Portfolio_Application.entity.Trade;
import lombok.Data;

@Data
public class TradeDTO {
    private long userId;
    private String tradeType;
    private long quantity;
    private String stockId;
}
