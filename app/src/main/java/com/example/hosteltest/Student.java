package com.example.hosteltest;


public class Student {
    public String name;
    public String phone;
    public String roll;
    public String cast;
    public String gmark;
    public String city;
    public int id;
    public int room;
    public boolean status;



    public Student() { }
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public String getGmark() {
        return gmark;
    }

    public void setGmark(String gmark) {
        this.gmark = gmark;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
