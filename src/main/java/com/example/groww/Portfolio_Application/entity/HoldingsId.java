package com.example.groww.Portfolio_Application.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
public class HoldingsId implements Serializable {
    private long userId;
    private String stockId;

    public HoldingsId(long id, String id1) {
        super();
        this.userId = id;
        this.stockId = id1;
    }

    // Constructors, getters, setters, equals, and hashCode methods
}

