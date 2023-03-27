package com.example.Parking.service.impl;

import com.example.Parking.dto.LogingDTO;
import com.example.Parking.exception.CustomeException;
import com.example.Parking.model.Account;
import com.example.Parking.outils.Config;
import com.example.Parking.repository.AccountRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginServiceImp {

   private AccountRepository accountRepository;

   @Autowired
    public LoginServiceImp(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    //TODO effectuer une connection + faire des test unitaire pour les methode creer

    //effectuer la connexion
    public boolean login(@NotNull LogingDTO logingDTO) {
        Optional<Account> loginAccount = checkExistingAccountByEmailOrUsernameReturnIt(logingDTO.getIdentifiant());
        if (loginAccount.isPresent() && Config.matches(logingDTO.getPassword(), loginAccount.get().getPassword())) {
            return true;
        } else {
            throw new CustomeException("Échec de la connexion");
        }
    }

    //Verifier la connexion avec le mail ou le pseudo(usename)
    private Optional<Account> checkExistingAccountByEmailOrUsernameReturnIt(@NotNull String emailOrUsername) {
        //recupere le mail dans la base de donnee

        Optional<Account> existingAccountByEmail = accountRepository.findByEmail(emailOrUsername);
        //recupere le le nom dans la base de donnee
        Optional<Account> existingAccountByUsername = accountRepository.findByUsername(emailOrUsername);
        //condition de verification mail et nom s'il existe dans la DBB on retourne
        if (existingAccountByEmail.isPresent()) {
            return existingAccountByEmail;
        } else if (existingAccountByUsername.isPresent()) {
            return existingAccountByUsername;
        } else {
            throw new CustomeException("Aucun compte trouvé pour cet identifiant : " + emailOrUsername);
        }
    }

}
