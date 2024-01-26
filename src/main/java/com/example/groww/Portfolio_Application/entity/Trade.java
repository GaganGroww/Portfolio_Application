package com.example.groww.Portfolio_Application.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
@Entity
@Data
public class Trade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="Quantity")
    private long quantity;

    @Column(name="Trade Type", nullable = false)
    private TradeType tradeType;

    @Column(name="Trade Price", nullable = false)
    private Float tradePrice;

    @Column(name="userId", nullable = false)
    private long userId;

    @Column(name="stockId", nullable = false)
    private String stockId;

    //NOT CHECKED
    private LocalDateTime createdAt = LocalDateTime.now();

    public enum TradeType {
        BUY, SELL
    }
    public long getUserId(){return userId;}
    public void setUserId(long userId){ this.userId = userId; }

    public String getStockId(){return stockId;}
    public void setUserId(String stockId){ this.stockId = stockId; }

    public Float getTradePrice() {
        return tradePrice;
    }

    public void setTradePrice(Float tradePrice) {
        this.tradePrice = tradePrice;
    }


    public TradeType getTradeType() {
        return tradeType;
    }

    public void setTradeType(TradeType tradeType) {
        this.tradeType = tradeType;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
}

