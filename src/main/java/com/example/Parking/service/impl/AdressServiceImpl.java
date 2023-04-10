package com.example.Parking.service.impl;

import com.example.Parking.dto.AdressDto;
import com.example.Parking.exception.CustomeException;
import com.example.Parking.model.Adress;
import com.example.Parking.repository.AdressRepository;
import com.example.Parking.service.AdressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdressServiceImpl implements AdressService {

    private AdressRepository adressRepository;

    @Autowired
    public AdressServiceImpl(AdressRepository adressRepository) {
        this.adressRepository = adressRepository;
    }

    public Adress createAdress(AdressDto adressDto) {
        // Vérifier si l'adresse existe déjà
        Optional<Adress> existingAdress = adressRepository.findByCityAndNameVoieAndNumVoieAndPostalCode(
                adressDto.getCity(),
                adressDto.getNameVoie(),
                adressDto.getNumVoie(),
                adressDto.getPostalCode());
        // Si l'adresse n'existe pas déjà, créer une nouvelle adresse
        if (existingAdress.isEmpty()) {
            Adress newAdress = AdressDto.mapAdressToEntity(adressDto);
            newAdress.setCity(adressDto.getCity());
            newAdress.setNumVoie(adressDto.getNumVoie());
            newAdress.setPostalCode(adressDto.getPostalCode());
            newAdress.setTypeVoie(adressDto.getTypeVoie());
            newAdress.setNameVoie(adressDto.getNameVoie());
            return adressRepository.save(newAdress);
        } else {
            throw new CustomeException("Address already exists",
                    "City: " + adressDto.getCity() +
                            ", NameVoie: " + adressDto.getNameVoie() +
                            ", NumVoie: " + adressDto.getNumVoie() +
                            ", PostalCode: " + adressDto.getPostalCode());
        }
    }


    public String findByCiTyName(String city) {
       Optional<Adress>  adress = adressRepository.findByCity(city);
        if (adress.isPresent()) {
            return adress.get().getCity();
        }else {
            throw new CustomeException("this City "+city+"not exitant");
        }
    }

    public List<Adress> getAllCity() {
        if ( adressRepository.findAll().isEmpty()) {
            throw new CustomeException("no city exist");
        }
        return adressRepository.findAll();
    }
    public Page<Adress> getAllCityByPage(){
        int pageNumber =0;
        int pageSize =10;
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        return adressRepository.findAll(pageable);
    }

}
