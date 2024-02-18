package com.example.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Vehicle {

    @Id
    @GeneratedValue
    long vehicleId;

    @Id
    @GeneratedValue
    String vehicle_alias;
    
    int vehicleType;

    enum VehicleType {
        AUTO(1),
        CAR_REGULAR(2),
        CAR_PREMIUM(3),
        BIKE(4);

        private int Value;

        public int getValue() {
            return Value;
        }

        VehicleType(int type) {
            this.Value = type;
        }
    }

    VehicleMetadata metadata;


    public Vehicle(int type, VehicleMetadata metadata) {
        super();
        this.vehicleType = type;
        this.metadata = metadata;
    }


    @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, 
        CascadeType.DETACH,  CascadeType.REFRESH})
    @JoinColumn(name = "owner_id", referencedColumnName =  "userId")
    private User owner;


    public User getOwner() {
        return owner;
    }


    public void setOwner(User owner) {
        this.owner = owner;
    }
    
}
