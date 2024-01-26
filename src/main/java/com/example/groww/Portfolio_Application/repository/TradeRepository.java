package com.example.groww.Portfolio_Application.repository;

import com.example.groww.Portfolio_Application.entity.Stocks;
import com.example.groww.Portfolio_Application.entity.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeRepository extends JpaRepository<Trade,Long> {
}
