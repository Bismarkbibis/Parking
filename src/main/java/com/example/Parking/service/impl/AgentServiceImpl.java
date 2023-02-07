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
import com.example.Parking.repository.AccountRepository;
import com.example.Parking.repository.AdressRepository;
import com.example.Parking.repository.AgentRepository;
import com.example.Parking.service.AgentService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AgentServiceImpl implements AgentService {

    @Autowired
    private final AgentRepository agentRepository;
    @Autowired
    private static final ModelMapper mapper = new ModelMapper();
    @Autowired
    private final AccountRepository accountRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final AdressRepository adressRepository;

    String regexEmail = "^[\\\\w!#$%&’*+/=?`{|}~^-]+(?:\\\\.[\\\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\\\.)+[a-zA-Z]{2,6}$";
    String regexPhoneNumber= "^(?:(?:\\+|00)33[\\s.-]{0,3}(?:\\(0\\)[\\s.-]{0,3})?|0)[1-9](?:(?:[\\s.-]?\\d{2}){4}|\\d{2}(?:[\\s.-]?\\d{3}){2})$";

   /// private CustomeException customeException;
    private HashMap<String,String> error;

    @Override
    public Agent creatAgent(AgentDto agentDto, AccountDto accountDto, AdressDto adressDTO) {

        //Variable
        Agent newAgent = null;
        //DTO to Entity
        Agent agentParam = mapAgentToEntity(agentDto);

        Optional<Agent> findAgentdBynNumeriSiret = agentRepository.findAgentByNumSire(agentParam.getSiretNumber());
        Optional<Agent> agentEmail = agentRepository.findByEmail(agentParam.getEmail());
        if (findAgentdBynNumeriSiret.isEmpty()) {

            newAgent = new Agent();
            newAgent.setName(agentParam.getName());
            if (agentParam.getNumber().matches(regexPhoneNumber)) {
                newAgent.setNumber(agentParam.getNumber());
            }else {
               error.put("Number incorrect",agentParam.getNumber());
            }
            //TODO a vérifier le numéro siret
            newAgent.setSiretNumber(agentParam.getSiretNumber());

            //verification de mail
            boolean emailVerification = agentParam.getEmail().matches(regexEmail);
            if (agentEmail.isEmpty() && emailVerification == true) {
                newAgent.setEmail(agentParam.getEmail());
            } else {
                //throw new CustomeException("Erreur", "Mail deja existant", agentParam.getEmail());
                error.put("Mail deja existant",agentParam.getEmail());
            }
            //creatAcount
            Account newAccount = getAccount(newAgent);

            if (accountDto.getPassword().matches(accountDto.getPassword02())) {
                newAccount.setPassword(passwordEncoder.encode(accountDto.getPassword()));
                newAccount.setAgent(newAgent);
            } else {
                //throw new CustomeException("Erreur", "Mots de pass incorrect", accountDto.getEmail());
                error.put("Mail deja existant",agentParam.getEmail());
            }

            //creatAdress
            Adress newAdress = getAdress(adressDTO);
            newAgent.setAdress(newAdress);

            accountRepository.save(newAccount);
            agentRepository.save(newAgent);
            adressRepository.save(newAdress);
            return newAgent;

        } else if (error.isEmpty()) {
            CustomeException customeException = new CustomeException(error,"error d'insection de l'agence");
            throw  customeException;
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
    // Acount
    private static AccountDto mapAcountToDTO(Account account){
        AccountDto accountDto = mapper.map(account,AccountDto.class);
        return accountDto;
    }

    private static Account mapAcountToEntity(AccountDto accountDto){
        Account account=mapper.map(accountDto,Account.class);
        return account;
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
