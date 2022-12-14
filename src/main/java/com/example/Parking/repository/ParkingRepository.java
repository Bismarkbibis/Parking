package com.example.Parking.repository;

import com.example.Parking.model.Parking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingRepository extends JpaRepository<Parking, Long> {
    Parking findAllByName (String name);
}