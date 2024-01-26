package com.example.groww.Portfolio_Application.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    private List<HoldingDTO> holdingDTO;
    private float totalProtfolioHolding;
    private float totalBuyPrice;
    private float totalReturns;
    private float totalReturnsPercentage;

}
