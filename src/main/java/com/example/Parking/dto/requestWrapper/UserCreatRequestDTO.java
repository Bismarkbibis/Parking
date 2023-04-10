package com.example.Parking.dto.requestWrapper;

import com.example.Parking.dto.AccountDto;
import com.example.Parking.dto.AdressDto;
import com.example.Parking.dto.AgentDto;
import com.example.Parking.dto.UserDTO;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserCreatRequestDTO implements Serializable {

    private AccountDto accountDto;
    private AgentDto agentDto;
    private AdressDto adressDto;
    private UserDTO userDTO;
}
