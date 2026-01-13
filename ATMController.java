package com.aur.atm.controller;

import com.aur.atm.model.Transaction;
import com.aur.atm.model.User;
import com.aur.atm.repository.TransactionRepository;
import com.aur.atm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ATMController {

    @Autowired private UserRepository userRepo;
    @Autowired private TransactionRepository transactionRepo;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String,String> data) {
        String username = data.get("username");
        String pin = data.get("pin");

        if (userRepo.existsByUsername(username)) {
            return ResponseEntity.badRequest().body("Username already exists");
        }

        User user = new User(username, pin);
        userRepo.save(user);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String,String> data) {
        User user = userRepo.findByUsernameAndPin(
                data.get("username"), data.get("pin"));
        return user != null
                ? ResponseEntity.ok(user)
                : ResponseEntity.status(401).body("Invalid credentials");
    }

    @PostMapping("/deposit")
    public ResponseEntity<?> deposit(@RequestBody Map<String,String> body) {
        int userId = Integer.parseInt(body.get("userId"));
        BigDecimal amount = new BigDecimal(body.get("amount"));

        User user = userRepo.findById(userId).orElse(null);
        if (user == null) return ResponseEntity.badRequest().body("User not found");

        user.setBalance(user.getBalance().add(amount));
        userRepo.save(user);
        transactionRepo.save(new Transaction(user, "deposit", amount));
        return ResponseEntity.ok(user);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<?> withdraw(@RequestBody Map<String,String> body) {
        int userId = Integer.parseInt(body.get("userId"));
        BigDecimal amount = new BigDecimal(body.get("amount"));

        User user = userRepo.findById(userId).orElse(null);
        if (user == null || user.getBalance().compareTo(amount) < 0)
            return ResponseEntity.badRequest().body("Insufficient balance");

        user.setBalance(user.getBalance().subtract(amount));
        userRepo.save(user);
        transactionRepo.save(new Transaction(user, "withdrawal", amount));
        return ResponseEntity.ok(user);
    }

    @GetMapping("/transactions/{userId}")
    public List<Transaction> getTransactions(@PathVariable int userId) {
        return transactionRepo.findByUserId(userId);
    }
}
