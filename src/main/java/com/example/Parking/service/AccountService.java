package com.example.Parking.service;

import com.example.Parking.dto.AccountDto;
import com.example.Parking.model.Account;
import com.example.Parking.model.User;

public interface AccountService {

    User creatUser (AccountDto accountDto,int idUser);
}
