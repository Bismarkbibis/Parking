package com.example.Parking.controller;

import com.example.Parking.dto.LogingDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/login/")
@CrossOrigin("*")
public class LoginController  {


    @PostMapping(path = "/user")
    public boolean connection(@RequestBody LogingDTO logingDTO){
        System.out.println(" "+logingDTO.getPassword()+" "+logingDTO.getIdentifiant());
        return true;
    }
}
