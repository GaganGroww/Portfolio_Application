package com.example.groww.Portfolio_Application.repository;

import com.example.groww.Portfolio_Application.entity.Stocks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stocks,String> {
}
