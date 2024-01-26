package com.example.groww.Portfolio_Application.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="Username")
    private String userName;

    @Column(name="Password")
    private String passWord;

    //NOT CHECKED
    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="userId",referencedColumnName = "id")
    private List<Trade> trade;

    public long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }
}
