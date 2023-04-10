package com.example.Parking.controller;

import com.example.Parking.dto.UserDTO;
import com.example.Parking.dto.requestWrapper.UserCreatRequestDTO;
import com.example.Parking.exception.CustomeException;
import com.example.Parking.model.User;
import com.example.Parking.service.impl.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/user/")
@CrossOrigin("*")
public class UserController {

    private UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }


    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserCreatRequestDTO creatRequestDTO) {
        try {
            User newUser = userService.creatCustomer(creatRequestDTO.getUserDTO(),creatRequestDTO.getAdressDto(),creatRequestDTO.getAccountDto());
            return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
        } catch (CustomeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
