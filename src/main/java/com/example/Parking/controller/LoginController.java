package com.example.Parking.controller;

import com.example.Parking.dto.LoginEnterDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/login/")
@CrossOrigin("*")
public class LoginController  {


    @PostMapping(path = "/user")
    public boolean connection(@RequestBody LoginEnterDTO logingDTO){
        System.out.println(" "+logingDTO.getPassword()+" "+logingDTO.getIdentifiant());
        return true;
    }
}
