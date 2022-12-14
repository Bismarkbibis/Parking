package com.example.Parking.repository;

import com.example.Parking.model.Adress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdressRepository extends JpaRepository<Adress, Long> {

    Optional<Adress> findAdressById(int id);
}