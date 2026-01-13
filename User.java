package com.aur.atm.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String username;
    private String pin;
    private BigDecimal balance = BigDecimal.ZERO;

    public User() {}

    public User(String username, String pin) {
        this.username = username;
        this.pin = pin;
        this.balance = BigDecimal.ZERO;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPin() { return pin; }
    public void setPin(String pin) { this.pin = pin; }

    public BigDecimal getBalance() { return balance; }
    public void setBalance(BigDecimal balance) { this.balance = balance; }
}
