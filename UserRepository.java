package com.aur.atm.repository;

import com.aur.atm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsernameAndPin(String username, String pin);
    boolean existsByUsername(String username);
}
