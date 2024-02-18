package com.example.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class User {

    @Id
    @GeneratedValue
    long userId;

    @Id
    @GeneratedValue
    String user_alias;
    
    String firstName;
    String lastName;
    String phoneNumber;

    @OneToMany(mappedBy = "owner", cascade= CascadeType.ALL)
    List<Vehicle> ownedVehicles = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<UserDocument> uploadedDocuments = new ArrayList<>();

    
    public User(String firstName, String lastName, String phoneNumber) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public long getUserId() {
        return userId;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void registerVehicle(Vehicle vehicle) {
        ownedVehicles.add(vehicle);
        vehicle.setOwner(this);
    }

    public void removeVehicle(Vehicle vehicle) {
    	if (ownedVehicles != null)
    		ownedVehicles.remove(vehicle);
    	//set the player field in the registration
    	vehicle.setOwner(null);
    }

    public void uploadedDocument(UserDocument userDocument) {
        uploadedDocuments.add(userDocument);
        userDocument.setUser(this);
    }

    public void removeDocument(UserDocument userDocument) {
    	if (uploadedDocuments != null)
    		uploadedDocuments.remove(userDocument);
    	//set the player field in the registration
    	userDocument.setUser(null);
    }

}
