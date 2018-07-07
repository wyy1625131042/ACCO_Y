package com.hqu.acco.accomodation.entity;

import java.util.Date;

public class Appointment extends RentApply {


    private Date meet;

    private String location;

    private Landlord landlord;

    private HouseInfo houseInfo;

    private Student student;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Date getMeet() {
        return meet;
    }

    public void setMeet(Date meet) {
        this.meet = meet;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Landlord getLandlord() {
        return landlord;
    }

    public void setLandlord(Landlord landlord) {
        this.landlord = landlord;
    }

    public HouseInfo getHouseInfo() {
        return houseInfo;
    }

    public void setHouseInfo(HouseInfo houseInfo) {
        this.houseInfo = houseInfo;
    }
}
