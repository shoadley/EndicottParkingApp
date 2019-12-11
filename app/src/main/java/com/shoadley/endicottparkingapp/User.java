package com.shoadley.endicottparkingapp;

import java.io.Serializable;

public class User implements Serializable {
    private String id;
    private String password;
    private String email;
    private String decal;
    private String homeBuilding;

    public User() {}

    public User(String id, String password, String email, String decal, String homeBuilding) {
        this.setId(id);
        this.setPassword(password);
        this.setEmail(email);
        this.setDecal(decal);
        this.setHomeBuilding(homeBuilding);
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

    public String getHomeBuilding() {
        return homeBuilding;
    }

    public void setHomeBuilding(String homeBuilding) {
        this.homeBuilding = homeBuilding;
    }
}
