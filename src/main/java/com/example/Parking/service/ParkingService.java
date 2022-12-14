package com.example.Parking.service;

import com.example.Parking.model.Parking;
import org.springframework.stereotype.Service;

public interface ParkingService {
    Parking saveParking(Parking parking);
    Parking addParking(Parking parking);
    Parking deletParking(Parking parking);
    void updateParking(Parking parking);


}
