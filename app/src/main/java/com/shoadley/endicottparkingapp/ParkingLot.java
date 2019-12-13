package com.shoadley.endicottparkingapp;

public class ParkingLot {
    private int facultySpots;
    private int studentSpots;
    private int commuterSpots;
    private int id;
    private int distanceBeacon;
    private int distanceStandish;
    private int distanceLSB;

    public ParkingLot() {}

    public ParkingLot(int facultySpots, int studentSpots, int commuterSpots, int id, int distanceBeacon, int distanceStandish, int distanceLSB) {
        this.setFacultySpots(facultySpots);
        this.setStudentSpots(studentSpots);
        this.setCommuterSpots(commuterSpots);
        this.setId(id);
        this.setDistanceBeacon(distanceBeacon);
        this.setDistanceStandish(distanceStandish);
        this.setDistanceLSB(distanceLSB);
    }

    public int getFacultySpots() {
        return facultySpots;
    }

    public void setFacultySpots(int facultySpots) {
        this.facultySpots = facultySpots;
    }

    public int getStudentSpots() {
        return studentSpots;
    }

    public void setStudentSpots(int studentSpots) {
        this.studentSpots = studentSpots;
    }

    public int getCommuterSpots() {
        return commuterSpots;
    }

    public void setCommuterSpots(int commuterSpots) {
        this.commuterSpots = commuterSpots;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDistanceBeacon() {
        return distanceBeacon;
    }

    public void setDistanceBeacon(int distanceBeacon) {
        this.distanceBeacon = distanceBeacon;
    }

    public int getDistanceStandish() {
        return distanceStandish;
    }

    public void setDistanceStandish(int distanceStandish) {
        this.distanceStandish = distanceStandish;
    }

    public int getDistanceLSB() {
        return distanceLSB;
    }

    public void setDistanceLSB(int distanceLSB) {
        this.distanceLSB = distanceLSB;
    }
}
