package com.example.Parking.service.impl;

import com.example.Parking.dto.AccountDto;
import com.example.Parking.emu.AcountStatu;
import com.example.Parking.exception.CustomeException;
import com.example.Parking.model.Account;
import com.example.Parking.outils.Config;
import com.example.Parking.repository.AccountRepository;
import com.example.Parking.service.AccountService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {


    private final AccountRepository accountRepository;


    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account createAccount(AccountDto accountDto) {
        // Vérifier l'entrée null
        Objects.requireNonNull(accountDto, "Account ne peut pas être null.");

        // Vérifier si un compte existe déjà avec l'email et le nom d'utilisateur
        checkExistingAccountByEmail(accountDto.getEmail());
        checkExistingAccountByUsername(accountDto.getUsername());
        // Créer un nouveau compte utilisateur
        Account newAccount = AccountDto.mapAcountToEntity(accountDto);
        String email = accountDto.getEmail();
        String password = accountDto.getPassword();
        String confirmPassword = accountDto.getPassword02();
        String username = accountDto.getUsername();

        // Vérifier le format de l'email
        if (Config.verifyEmailFormat(email)) {
            newAccount.setEmail(email);
        } else {
            throw new CustomeException("Format d'email invalide : " + email);
        }

        // Vérifier que les mots de passe correspondent
        if (password.equals(confirmPassword)) {
            newAccount.setPassword(Config.encode(password));
        } else {
            throw new CustomeException("Les mots de passe ne correspondent pas.");
        }
        // Activer le compte
        newAccount.setStatus(AcountStatu.ACTIVE);
        // Définir le nom d'utilisateur et la date de création du compte
        newAccount.setUsername(username);
        newAccount.setDateCreation(LocalDate.now());
        // Enregistrer le nouveau compte utilisateur
        return accountRepository.save(newAccount);
    }

    @Override
    public List<Account> ACCOUNT_LIST(){
       List<Account> list =accountRepository.findAll();
        if (list.isEmpty()) {
            throw new CustomeException("Account empty");
        }
        return list;
    }

    private void checkExistingAccountByEmail(@NotNull String email) {
        Optional<Account> existingAccount = accountRepository.findByEmail(email);
        if (existingAccount.isPresent()) {
            throw new CustomeException("Un compte existe déjà avec cet email : " + email);
        }
    }

    private void checkExistingAccountByUsername(@NotNull String username) {
        Optional<Account> existingAccount = accountRepository.findByUsername(username);
        if (existingAccount.isPresent()) {
            throw new CustomeException("Un compte existe déjà avec ce nom d'utilisateur : " + username);
        }
    }

}


