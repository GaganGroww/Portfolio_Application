package com.example.groww.Portfolio_Application.controller;

import com.example.groww.Portfolio_Application.dto.StockDTO;
import com.example.groww.Portfolio_Application.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
@RestController
@RequestMapping("/Stock")
public class StockController {
    @Autowired
    private StockService stockService;

    @GetMapping(value = "{stockId}")
    private ResponseEntity<StockDTO> getStock(@PathVariable("stockId") String stockId){
        return new ResponseEntity<StockDTO> (stockService.getStock(stockId), HttpStatus.OK);
    }

    @PostMapping(value = "/update")
    public String handleCsvUpload(@RequestBody MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return "File is empty";
        }
        else {
            return stockService.getcsv(file);
        }
    }
}
