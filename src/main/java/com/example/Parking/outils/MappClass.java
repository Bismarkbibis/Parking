package com.example.Parking.outils;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MappClass {
    @Autowired
    public static final ModelMapper mapper = new ModelMapper();




}
