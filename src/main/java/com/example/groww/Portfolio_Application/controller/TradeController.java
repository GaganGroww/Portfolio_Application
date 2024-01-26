package com.example.groww.Portfolio_Application.controller;

import com.example.groww.Portfolio_Application.dto.TradeDTO;
import com.example.groww.Portfolio_Application.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Trade")
public class TradeController {

    @Autowired
    private TradeService tradeService;
    @PostMapping("/add")
    private ResponseEntity<String> addTrade(@RequestBody TradeDTO tradeDTO){
        return new ResponseEntity<String>(tradeService.convertToTradedto(tradeDTO), HttpStatus.OK);
    }
}
