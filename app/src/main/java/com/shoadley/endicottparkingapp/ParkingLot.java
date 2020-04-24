package com.shoadley.endicottparkingapp;

public class ParkingLot {
    private int facultySpots;
    private int studentSpots;
    private int commuterSpots;
    private int id;

    public ParkingLot() {}

    public ParkingLot(int facultySpots, int studentSpots, int commuterSpots, int id) {
        this.setFacultySpots(facultySpots);
        this.setStudentSpots(studentSpots);
        this.setCommuterSpots(commuterSpots);
        this.setId(id);
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

}
