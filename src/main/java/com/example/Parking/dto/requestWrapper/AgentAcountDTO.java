package com.example.Parking.dto.requestWrapper;

import com.example.Parking.dto.AccountDto;
import com.example.Parking.dto.AdressDto;
import com.example.Parking.dto.AgentDto;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AgentAcountDTO {

    private AccountDto accountDtos;
    private AgentDto agentDto;
    private AdressDto adressDto;
}
