package com.example.Parking.service;

import com.example.Parking.dto.AccountDto;
import com.example.Parking.model.Account;

import java.util.List;

public interface AccountService {


    //TODO : A revoir email et customer
    Account createAccount(AccountDto accountDto);
    List<Account> ACCOUNT_LIST();


}
