package com.example.groww.Portfolio_Application.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import lombok.Data;
import java.util.List;

@Data
@Entity
public class Stocks {
    @Id
    @Column(name="Id",unique = true,nullable = false)
    private String id;
    @Column(name="Stock Name",nullable = false)
    private String name;

    @Column(name="Opening Price",nullable = false)
    private Float openPrice;

    @Column(name="Closing Price",nullable = false)
    private Float closePrice;

    @Column(name="Last Price",nullable = false)
    private Float lastPrice;

    //NOT CHECKED
    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="stockId",referencedColumnName = "id")
    private List<Trade> trade;

}

