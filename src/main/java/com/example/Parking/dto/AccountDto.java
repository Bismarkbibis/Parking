package com.example.Parking.dto;

import com.example.Parking.emu.AcountStatu;
import com.example.Parking.emu.Role;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link com.example.Parking.model.Account} entity
 */
@Data
public class AccountDto implements Serializable {
    private final String password;
    private final String password02;
    private final String email;
}