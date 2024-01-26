package com.example.groww.Portfolio_Application.dto;

import lombok.Data;

@Data
public class StockDTO {
    private String id;
    private String name;
    private Float openPrice;
    private Float closePrice;
    private Float lastPrice;
}
