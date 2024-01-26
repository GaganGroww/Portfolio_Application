package com.example.groww.Portfolio_Application.dto;

import lombok.Data;

@Data
public class HoldingDTO {
    private String StockId;
    private String StockName;
    private long quantity;
    private float buyPrice;
    private float currentPrice;
    private float returns;
}
