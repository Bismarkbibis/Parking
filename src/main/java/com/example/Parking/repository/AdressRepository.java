package com.example.Parking.repository;

import com.example.Parking.model.Adress;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AdressRepository extends JpaRepository<Adress, Long> {

    Optional<Adress> findAdressById(int id);

    /**
     * @param city
     * @param nameVoie
     * @param numVoie
     * @param postalCode
     * @return
     */
    Optional<Adress> findByCityAndNameVoieAndNumVoieAndPostalCode(String city, String nameVoie, String numVoie, String postalCode);
    Optional<Adress> findByCity(String cityName);
    Page<Adress>findAll(Pageable pageable);

}