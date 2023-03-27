package com.example.Parking.controller;


import com.example.Parking.dto.AccountDto;
import com.example.Parking.model.Account;
import com.example.Parking.service.impl.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping(path = "/account/")
@RestController
@CrossOrigin("*")
public class AccountController {

    private final AccountServiceImpl accountService;

    @Autowired
    public AccountController(AccountServiceImpl accountService) {
        this.accountService = accountService;
    }

    @GetMapping(path = "/find/all")
    public ResponseEntity<List<Account>> getAllAccount(){
        return ResponseEntity.ok(accountService.ACCOUNT_LIST());
    }
    @PostMapping(path = "/creat")
    public ResponseEntity<Account> createAccount(@RequestBody AccountDto accountDto){
        Account account = accountService.createAccount(accountDto);
        return ResponseEntity.ok(account);
    }
}
