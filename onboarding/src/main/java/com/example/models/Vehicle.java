package com.example.models;

public class Vehicle {

    long vehicleId;

    String vehicle_alias;
    
    VehicleType type;

    enum VehicleType {
        AUTO,
        CAR_REGULAR,
        CAR_PREMIUM,
        BIKE
    }

    VehicleMetadata metadata;
    
}
