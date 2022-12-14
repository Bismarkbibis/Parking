package com.example.Parking.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link com.example.Parking.model.Adress} entity
 */
@Data
public class AdressDto implements Serializable {
    private final String numVoie;
    private final String nameVoie;
    private final String typeVoie;
    private final String postalCode;
    private final String city;

}