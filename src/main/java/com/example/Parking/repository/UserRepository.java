package com.example.Parking.repository;

import com.example.Parking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Integer, User> {
    @Query("SELECT u from User u WHERE u.id=:idUser")
    Optional<User> findAllById(int idUser);
}
