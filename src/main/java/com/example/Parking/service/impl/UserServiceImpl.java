package com.example.Parking.service.impl;

import com.example.Parking.dto.AccountDto;
import com.example.Parking.dto.AdressDto;
import com.example.Parking.dto.UserDTO;
import com.example.Parking.emu.Role;
import com.example.Parking.exception.CustomeException;
import com.example.Parking.model.Account;
import com.example.Parking.model.Adress;
import com.example.Parking.model.User;
import com.example.Parking.repository.UserRepository;
import com.example.Parking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AdressServiceImpl adressService;
    private final AccountServiceImpl accountService;

    public UserServiceImpl(UserRepository userRepository, AdressServiceImpl adressService, AccountServiceImpl accountService) {
        this.userRepository = userRepository;
        this.adressService = adressService;
        this.accountService = accountService;
    }

    @Override
    public User creatCustomer(UserDTO userDTO, AdressDto adressDto, AccountDto accountDto)throws CustomeException {
        //TODO utilise Map

        //verification des donnee
        validateAddressDto(adressDto);
        validateUserDTO(userDTO);
        // recupere le user
        Optional<User> userOption = userRepository.findAllByNumber(userDTO.getNumber());
        if (userOption.isPresent()) {
            throw new CustomeException("User already present");
        }

        User newUser = new User();
        newUser.setName(userDTO.getName());
        newUser.setFirstName(userDTO.getFirstName());
        newUser.setNumber(userDTO.getNumber());
        //TODO utilise Map
        Adress newAdress =adressService.createAdress(adressDto);
        newUser.setAdress(newAdress);
        //TODO utilise Map
        Account newAccount =accountService.createAccount(accountDto);
        newAccount.setRole(Role.USER);
        userRepository.save(newUser);
        return newUser;
    }
    private void validateUserDTO(UserDTO userDTO) throws CustomeException {
        // Vérifier que le nom et le prénom ne sont pas vides
        if (StringUtils.isEmpty(userDTO.getName()) || StringUtils.isEmpty(userDTO.getFirstName())) {
            throw new CustomeException("Le nom et le prénom de l'utilisateur ne peuvent pas être vides.");
        }
    }

    private void validateAddressDto(AdressDto addressDto) throws CustomeException {
        // Vérifier que tous les champs de l'adresse sont remplis
        if (StringUtils.isEmpty(addressDto.getCity()) || StringUtils.isEmpty(addressDto.getPostalCode()) || StringUtils.isEmpty(addressDto.getNameVoie())|| StringUtils.isEmpty(addressDto.getTypeVoie())) {
            throw new CustomeException("L'adresse doit comporter une rue, une ville et un code postal.");
        }
    }

    @Override
    public User findCustomer(String siret) {
        return null;
    }

    @Override
    public void deleteCustomer(int id) {

    }
}
