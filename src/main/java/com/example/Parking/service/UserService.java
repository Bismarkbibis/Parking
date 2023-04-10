package com.example.Parking.service;

import com.example.Parking.dto.AccountDto;
import com.example.Parking.dto.AdressDto;
import com.example.Parking.dto.UserDTO;
import com.example.Parking.model.User;

public interface UserService {

    User creatCustomer (UserDTO userDTO, AdressDto adressDto, AccountDto accountDto);
    User findCustomer(String siret);
    void deleteCustomer(int id);

    interface ParkingService {
    }
}
