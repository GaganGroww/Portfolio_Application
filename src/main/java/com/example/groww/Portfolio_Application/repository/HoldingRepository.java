package com.example.groww.Portfolio_Application.repository;

import com.example.groww.Portfolio_Application.entity.Holdings;

import com.example.groww.Portfolio_Application.entity.HoldingsId;
import com.example.groww.Portfolio_Application.entity.Stocks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HoldingRepository extends JpaRepository<Holdings, HoldingsId> {
    List<Holdings> findAllByUserId(long userId);
}
