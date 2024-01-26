package com.example.groww.Portfolio_Application.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@IdClass(HoldingsId.class)
public class Holdings {
    @Id
    @Column(name="userId", nullable = false)
    private long userId;

    @Id
    @Column(name="stockId", nullable = false)
    private String stockId;

    @Column(name="Quantity")
    private long quantity;
    @Column(name="Total_Buy_Price")
    private float totalBuyPrice;
    @Column(name="Returns")
    private float returns;
    @Column(name="Last_Updated",nullable = false)
    private LocalDateTime lastUpdated = LocalDateTime.now();
}
