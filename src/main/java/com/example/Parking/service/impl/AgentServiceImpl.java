package com.example.Parking.service.impl;

import com.example.Parking.dto.AccountDto;
import com.example.Parking.dto.AdressDto;
import com.example.Parking.dto.AgentDto;
import com.example.Parking.exception.CustomeException;
import com.example.Parking.model.Account;
import com.example.Parking.model.Adress;
import com.example.Parking.model.Agent;
import com.example.Parking.repository.AccountRepository;
import com.example.Parking.repository.AdressRepository;
import com.example.Parking.repository.AgentRepository;
import com.example.Parking.service.AgentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AgentServiceImpl implements AgentService {

    @Autowired
    private AgentRepository agentRepository;

    @Autowired
    private static final ModelMapper mapper = new ModelMapper();

    @Autowired
    private AccountRepository accountRepository;


    @Autowired
    private AdressRepository adressRepository;

    private AdressServiceImpl adressService;
    private AccountServiceImpl accountService;

    private HashMap<String, String> error = new HashMap<>();
    String regexPhoneNumber= "^(?:(?:\\+|00)33[\\s.-]{0,3}(?:\\(0\\)[\\s.-]{0,3})?|0)[1-9](?:(?:[\\s.-]?\\d{2}){4}|\\d{2}(?:[\\s.-]?\\d{3}){2})$";

    public AgentServiceImpl(AgentRepository agentRepository, AccountRepository accountRepository, AdressRepository adressRepository, AdressServiceImpl adressService, AccountServiceImpl accountService) {
        this.agentRepository = agentRepository;
        this.accountRepository = accountRepository;
        this.adressRepository = adressRepository;
        this.adressService = adressService;
        this.accountService = accountService;
    }

    @Override
    public Agent creatAgent(AgentDto agentDto, AccountDto accountDto, AdressDto adressDTO) {

        //Variable
        Agent newAgent = null;
        //DTO to Entity
        Agent agentParam = mapAgentToEntity(agentDto);

        Optional<Agent> findAgentdBynNumeriSiret = agentRepository.findAgentByNumSire(agentParam.getSiretNumber());
        Agent findAgentById = agentRepository.findById(agentParam.getId()).orElse(null);
        Optional<Agent> agentEmail = agentRepository.findByEmail(agentParam.getEmail());

        if (findAgentdBynNumeriSiret.isPresent()) {

            error.put("SiretNumber already exists", agentParam.getSiretNumber());

        } else if (findAgentById != null && findAgentById.getAccounts() != null) {

            error.put("Account already exists for agent", agentParam.getName());

        } else if (agentEmail.isPresent()) {

            error.put("Email already exists", agentParam.getEmail());

        } else {

            newAgent = new Agent();
            newAgent.setName(agentParam.getName());
            if (agentParam.getNumber().matches(regexPhoneNumber)) {
                newAgent.setNumber(agentParam.getNumber());
            } else {
                error.put("Number incorrect", agentParam.getNumber());
            }
            //TODO a vérifier le numéro siret
            newAgent.setSiretNumber(agentParam.getSiretNumber());

            //creatAcount
            Account newAccount = accountService.createAccount(accountDto);

            //creatAdress
            Adress newAdress = adressService.createAdress(adressDTO);
            newAgent.setAdress(newAdress);

            accountRepository.save(newAccount);
            agentRepository.save(newAgent);
            adressRepository.save(newAdress);
        }

        if (!error.isEmpty()) {
            CustomeException customeException = new CustomeException(error, "error d'insection de l'agence");
            throw customeException;
        }

        return newAgent;
    }


    public List<Agent> getAllAgent(){
        List<Agent> agents = agentRepository.findAll();
        return agents;
    }

    public Optional<Agent> getByName(String name){
        Optional<Agent> agent =agentRepository.findByName(name);
        return agent;
    }

    public void deleteAgent(String numSeret){
        Optional<Agent> agent = agentRepository.findAgentByNumSire(numSeret);
    }


    //DTO to Entity
    private static AgentDto mapToDTO(Agent agent){
        AgentDto agentDto=mapper.map(agent,AgentDto.class);
        return  agentDto;
    }
    //Entity to DTO
    private static Agent mapAgentToEntity(AgentDto agentDto){
        Agent agent=mapper.map(agentDto,Agent.class);
        return agent;
    }

    private static AdressDto mapAdressToDTO(Adress adress){
        AdressDto adressDto = mapper.map(adress,AdressDto.class);
        return adressDto;
    }

    private static Adress mapAdresseToEntity(AdressDto adressDto){
        Adress adress = mapper.map(adressDto,Adress.class);
        return adress;
    }
}
