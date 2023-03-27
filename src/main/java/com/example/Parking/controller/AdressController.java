package com.example.Parking.controller;

import com.example.Parking.dto.AdressDto;
import com.example.Parking.model.Adress;
import com.example.Parking.service.impl.AdressServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/adress/")
@CrossOrigin("*")
public class AdressController {

    private AdressServiceImpl adressService;

    @Autowired
    public AdressController(AdressServiceImpl adressService) {
        this.adressService = adressService;
    }

    @GetMapping(path = "/{city}")
    public ResponseEntity<String> searchByCity(@PathVariable String city){
        String adress = adressService.findByCiTyName(city);
        return ResponseEntity.ok(adress);
    }
    @GetMapping(path = "/all")
    public ResponseEntity<List<Adress>>getAllCity(){
        List<Adress> listOfCity= adressService.getAllCity();
        return ResponseEntity.ok(
                listOfCity
        );
    }
    @PostMapping("/creat")
    ResponseEntity<Adress> getNewAdress(@RequestBody AdressDto adressDto){
        Adress adress = adressService.createAdress(adressDto);
        return ResponseEntity.ok(adress);
    }

}
