package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.repository.VehicleRepository;

public class VehicleService {
    @Autowired
    VehicleRepository vehicleRepository;
}
