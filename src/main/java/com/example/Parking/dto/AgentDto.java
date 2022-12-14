package com.example.Parking.dto;

import com.example.Parking.model.Agent;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link Agent} entity
 */
@Data
public class AgentDto implements Serializable {
    private final String name;
    private final String number;
    private final String siretNumber;
    private final String email;

}