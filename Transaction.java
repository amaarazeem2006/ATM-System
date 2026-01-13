package com.aur.atm.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private User user;

    private String type;
    private BigDecimal amount;
    private LocalDateTime createdAt = LocalDateTime.now();

    public Transaction() {}

    public Transaction(User user, String type, BigDecimal amount) {
        this.user = user;
        this.type = type;
        this.amount = amount;
    }

    public int getId() { return id; }
    public User getUser() { return user; }
    public String getType() { return type; }
    public BigDecimal getAmount() { return amount; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
