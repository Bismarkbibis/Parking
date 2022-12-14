package com.example.Parking.repository;

import com.example.Parking.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query("SELECT e fROM Agent e where e.email=:emailAgent")
    Optional<Account> findByEmail(String emailAgent);
}