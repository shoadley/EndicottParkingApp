package com.shoadley.endicottparkingapp;

import java.io.Serializable;

public class User implements Serializable {
    private String id;
    private String password;
    private String email;
    private String decal;
    private String building;
    private String currentLot;

    public User() {}

    public User(String id, String password, String email, String decal, String homeBuilding, String currentLot) {
        this.setId(id);
        this.setPassword(password);
        this.setEmail(email);
        this.setDecal(decal);
        this.setBuilding(homeBuilding);
        this.setCurrentLot(currentLot);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDecal() {
        return decal;
    }

    public void setDecal(String decal) {
        this.decal = decal;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String homeBuilding) {
        this.building = homeBuilding;
    }

    public String getCurrentLot() {
        return currentLot;
    }

    public void setCurrentLot(String currentLot) {
        this.currentLot = currentLot;
    }
}
