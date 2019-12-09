package com.shoadley.endicottparkingapp;

public class User {
    private int id;
    private String password;
    private String email;
    private String decal;
    private String homeBuilding;

    public User() {}

    public User(int id, String password, String email, String decal, String homeBuilding) {
        this.setId(id);
        this.setPassword(password);
        this.setEmail(email);
        this.setDecal(decal);
        this.setHomeBuilding(homeBuilding);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
