package com.example.groww.Portfolio_Application.service;

import com.example.groww.Portfolio_Application.dto.StockDTO;
import com.example.groww.Portfolio_Application.entity.Stocks;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface StockService {
    StockDTO getStock(String id);

    StockDTO convertToStockdto(Stocks stock);
    String getcsv(MultipartFile file) throws IOException;
}
