package com.example.Parking.service.impl;

import com.example.Parking.dto.AccountDto;
import com.example.Parking.dto.AdressDto;
import com.example.Parking.dto.AgentDto;
import com.example.Parking.emu.Role;
import com.example.Parking.exception.CustomeException;
import com.example.Parking.model.Account;
import com.example.Parking.model.Adress;
import com.example.Parking.model.Agent;
import com.example.Parking.outils.Config;

import com.example.Parking.repository.AgentRepository;
import com.example.Parking.service.AgentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AgentServiceImpl implements AgentService {

    private AgentRepository agentRepository;
    private static final ModelMapper mapper = new ModelMapper();
    private final AdressServiceImpl adressService;
    private final AccountServiceImpl accountService;


    public AgentServiceImpl(AgentRepository agentRepository, AdressServiceImpl adressService, AccountServiceImpl accountService) {
        this.agentRepository = agentRepository;
        this.adressService = adressService;
        this.accountService = accountService;
    }

    @Override
    public Agent createAgent(AgentDto agentDto, AdressDto adressDTO, AccountDto accountDto) {
        // Vérification de l'unicité du numéro SIRET et de l'email
        Optional<Agent> agentByNumSiret = agentRepository.findAgentByNumSire(agentDto.getSiretNumber());
        Optional<Agent> agentByEmail = agentRepository.findByEmail(agentDto.getEmail());

        if (agentByNumSiret.isPresent()) {
            throw new CustomeException("Le numéro SIRET existe déjà : " + agentDto.getSiretNumber());
        } else if (agentByEmail.isPresent()) {
            throw new CustomeException("L'adresse email existe déjà : " + agentDto.getEmail());
        }

        // Création d'un nouvel agent
        Agent newAgent = mapAgentToEntity(agentDto);

        // Vérification et ajout du numéro de téléphone
        if (Config.isValidPhoneNumber(agentDto.getNumber())) {
            newAgent.setNumber(agentDto.getNumber());
        } else {
            throw new CustomeException("Numéro de téléphone invalide : " + agentDto.getNumber());
        }

        // Création d'une nouvelle adresse
        Adress newAddress = adressService.createAdress(adressDTO);
        newAgent.setAdress(newAddress);

        // Création d'un nouveau compte et ajout de l'agent
        Account newAccount = accountService.createAccount(accountDto);
        newAccount.setAgent(newAgent);
        newAccount.setRole(Role.ADMIN);

        // Sauvegarde de l'agent et retour de l'objet
        agentRepository.save(newAgent);
        return newAgent;
    }



    public List<Agent> getAllAgent(){
        return agentRepository.findAll();
    }

    public Optional<Agent> getByName(String name){
        return agentRepository.findByName(name);
    }

    public void deleteAgent(String numSeret){
        Optional<Agent> agent = agentRepository.findAgentByNumSire(numSeret);
    }


    //Entity to DTO
    private static Agent mapAgentToEntity(AgentDto agentDto){
        return mapper.map(agentDto,Agent.class);
    }

}
