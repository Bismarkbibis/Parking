package com.example.Parking.repository;

import com.example.Parking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u from User u WHERE u.number=:userNumber")
    Optional<User> findAllByNumber(String userNumber);
}
