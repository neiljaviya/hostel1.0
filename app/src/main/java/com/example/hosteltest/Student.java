package com.example.hosteltest;


public class Student {
    public String name;
    public String phone;
    public String roll;
    public String cast;
    public String acpcrank;
    public String city;
    public int id;
    public int room;
    public boolean status;
    public String sendemail;
    public String parent_email;
    private String detail;
    private String Imageid;
    private String Spinnertext;



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

    public String getParent_email() {
        return parent_email;
    }

    public void setParent_email(String parent_email) {
        this.parent_email = parent_email;
    }

    public String getSendemail() {
        return sendemail;
    }

    public void setSendemail(String sendemail) {
        this.sendemail = sendemail;
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

    public String getAcpcrank() {
        return acpcrank;
    }

    public void setAcpcrank(String acpcrank) {
        this.acpcrank = acpcrank;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSpinnertext() {
        return Spinnertext;
    }

    public void setSpinnertext(String spinnertext) {
        Spinnertext = spinnertext;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getImageid() {
        return Imageid;
    }

    public void setImageid(String imageid) {
        Imageid = imageid;
    }
}
