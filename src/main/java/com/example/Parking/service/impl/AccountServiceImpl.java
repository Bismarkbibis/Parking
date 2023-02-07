package com.example.Parking.service.impl;

import com.example.Parking.dto.AccountDto;
import com.example.Parking.exception.CustomeException;
import com.example.Parking.model.Account;
import com.example.Parking.model.User;
import com.example.Parking.outils.UserConfig;
import com.example.Parking.repository.AccountRepository;
import com.example.Parking.repository.UserRepository;
import com.example.Parking.service.AccountService;

import java.util.HashMap;
import java.util.Optional;

public class AccountServiceImpl implements AccountService {

    //
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    private UserConfig userConfig;
    private HashMap<String,String> error;

    public AccountServiceImpl(AccountRepository accountRepository, UserRepository userRepository, UserConfig userConfig) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
        this.userConfig = userConfig;
    }

    //TODO : A revoir email et customer
    @Override
    public User creatUser(AccountDto accountDto,int idUser) {

        //seach user is present pass else exception throw
        Account findUserByEmail=accountRepository.findByEmail(accountDto.getEmail()).
                orElseThrow(()->new CustomeException("not find","value find = "+accountDto.getEmail()));
        //seach user is present pass else exception throw
        User findUserById = userRepository.findAllById(idUser).
                orElseThrow(()->new CustomeException("not find","id :  "+idUser));

            //creat account
            Account newAccount = new Account();
            if (userConfig.verifyEmailFormat(accountDto.getEmail())){
                newAccount.setEmail(accountDto.getEmail());
            }else{

            }
            // verifie password
            if (userConfig.matches(accountDto.getPassword(), accountDto.getPassword02())) {
                newAccount.setPassword(accountDto.getPassword());
                userConfig.encode(newAccount.getPassword());
            }
        return null;
        }


    }

