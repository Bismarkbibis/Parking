package com.example.Parking.repository;

import com.example.Parking.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query("SELECT a fROM Account a where a.email=:emailAgent")
    Optional<Account> findByEmail(String emailAgent);

    Optional<Account> findByUsername(String username);
}