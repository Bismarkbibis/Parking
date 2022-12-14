package com.example.Parking.service;

import com.example.Parking.model.User;

public interface UserService {

    User creatCustomer (User user);
    User findCustomer(String siret);
    void deleteCustomer(int id);
}
