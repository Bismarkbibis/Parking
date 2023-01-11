package com.example.Parking.service.impl;

import com.example.Parking.dto.AccountDto;
import com.example.Parking.dto.AdressDto;
import com.example.Parking.dto.AgentDto;
import com.example.Parking.emu.AcountStatu;
import com.example.Parking.emu.Role;
import com.example.Parking.exception.CustomeException;
import com.example.Parking.model.Account;
import com.example.Parking.model.Adress;
import com.example.Parking.model.Agent;
import com.example.Parking.model.Token;
import com.example.Parking.repository.AccountRepository;
import com.example.Parking.repository.AdressRepository;
import com.example.Parking.repository.AgentRepository;
import com.example.Parking.repository.TokenRepository;
import com.example.Parking.service.AgentService;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class AgentServiceImpl implements AgentService {

    private static final ModelMapper mapper = new ModelMapper();


    private final AgentRepository agentRepository;
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final AdressRepository adressRepository;
    private final TokenRepository tokenRepository;
    private final String regexPhoneNumber = "^(?:(?:\\+|00)33[\\s.-]{0,3}(?:\\(0\\)[\\s.-]{0,3})?|0)[1-9](?:(?:[\\s.-]?\\d{2}){4}|\\d{2}(?:[\\s.-]?\\d{3}){2})$";

    /// private CustomeException customeException;
    private HashMap<String, String> error;

    public AgentServiceImpl(AgentRepository agentRepository, AccountRepository accountRepository, PasswordEncoder passwordEncoder, AdressRepository adressRepository, TokenRepository tokenRepository) {
        this.agentRepository = agentRepository;
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
        this.adressRepository = adressRepository;
        this.tokenRepository = tokenRepository;
    }

    @Override
    public Agent creatAgent(AgentDto agentDto, AccountDto accountDto, AdressDto adressDTO) {

        //Variable
        Agent newAgent = null;
        //DTO to Entity
        Agent agentParam = mapAgentToEntity(agentDto);

        Optional<Agent> findAgentdBynNumeriSiret = agentRepository.findAgentByNumSire(agentParam.getSiretNumber());
        Optional<Agent> findAgentByEmail = agentRepository.findByEmail(agentParam.getEmail());

        // verify email of agency
        if (findAgentdBynNumeriSiret.isEmpty()) {
            newAgent = new Agent();
            newAgent.setName(agentParam.getName());
            //verify phone number
            if (agentParam.getNumber().matches(regexPhoneNumber)) {
                newAgent.setNumber(agentParam.getNumber());
            } else {
                error.put("Number incorrect", agentParam.getNumber());
            }
            //TODO a vérifier le numéro siret
            newAgent.setSiretNumber(agentParam.getSiretNumber());

            //verify de mail
           boolean emailUser = isValidEmail(agentParam.getEmail());

            if (findAgentByEmail.isEmpty() && emailUser) {
                log.info("Email passeer");
                    newAgent.setEmail(agentParam.getEmail());
            } else {
                error.put("Mail deja existant", agentParam.getEmail());
            }

            //creatAcount
            Account newAccount = getAccount(newAgent);
            //Token
            Token token = genererToken();

            if (accountDto.getPassword().matches(accountDto.getPassword02())) {
                newAccount.setPassword(passwordEncoder.encode(accountDto.getPassword()));
                newAccount.setToken(token);
                newAccount.setAgent(newAgent);
            } else {
                error.put("Mail deja existant", agentParam.getEmail());
            }


            //creatAdress
            Adress newAdress = getAdress(adressDTO);
            newAgent.setAdress(newAdress);

            tokenRepository.save(token);
            accountRepository.save(newAccount);
            agentRepository.save(newAgent);
            adressRepository.save(newAdress);

        } else if (!(error.isEmpty())) {
            log.info("hello mamamma");
            throw new CustomeException(error, "error d'insection de l'agence");
        }
        return newAgent;
    }


    @NotNull
    private static Account getAccount(Agent newAgent) {
        Account newAccount = new Account();
        newAccount.setEmail(newAgent.getEmail());
        newAccount.setRole(Role.AMDIN);
        newAccount.setStatu(AcountStatu.ACTIVE);

        return newAccount;
    }

    @NotNull
    private static Adress getAdress(AdressDto adressDTO) {
        Adress newAdress = new Adress();
        newAdress.setCity(adressDTO.getCity());
        newAdress.setNumVoie(adressDTO.getNameVoie());
        newAdress.setPostalCode(adressDTO.getPostalCode());
        newAdress.setTypeVoie(adressDTO.getTypeVoie());
        newAdress.setNameVoie(adressDTO.getNameVoie());
        return newAdress;
    }
    public static boolean isValidEmail( String email ) {
        String regExp = "^[A-Za-z0-9._-]+@[A-Za-z0-9._-]+\\.[a-z][a-z]+$";
        return email.matches( regExp );
    }

    @NotNull
    private Token genererToken() {
        UUID uuid = UUID.randomUUID();
        String valeurToken = uuid.toString(); // le token envoyer part le toString

        Calendar calendar = Calendar.getInstance(); // permet de recupere la date et l'heur actuelle
        // la durere du token
        // on le met 15min
        calendar.add(Calendar.MINUTE, 30); // la durer
        Date expirationDate = calendar.getTime(); // constrution de date

        return new Token(valeurToken, expirationDate);
    }

    public List<Agent> getAllAgent() {
        return agentRepository.findAll();
    }

    public Optional<Agent> getByName(String name) {
        return agentRepository.findByName(name);
    }


    //Entity to DTO
    private static Agent mapAgentToEntity(AgentDto agentDto) {
        return mapper.map(agentDto, Agent.class);
    }

    // Acount
    private static AccountDto mapAcountToDTO(Account account) {
        AccountDto accountDto = mapper.map(account, AccountDto.class);
        return accountDto;
    }

}
