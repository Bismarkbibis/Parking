package com.example.Parking.service;

import com.example.Parking.dto.AccountDto;
import com.example.Parking.dto.AdressDto;
import com.example.Parking.dto.AgentDto;
import com.example.Parking.model.Agent;

public interface AgentService {

    Agent creatAgent(AgentDto agentDto, AccountDto accountDto, AdressDto adressDto);

}
