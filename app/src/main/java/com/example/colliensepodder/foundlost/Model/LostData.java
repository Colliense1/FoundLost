package com.example.colliensepodder.foundlost.Model;

public class LostData {
    String departmentName;
    String phoneNumber;
    String address;
    String description;
    String email;
    String lostInfo;
    String yourId;

    public LostData(String departmentName, String phoneNumber, String address, String yourId, String description, String email, String lostInfo) {
        this.departmentName = departmentName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.yourId = yourId;
        this.description = description;
        this.email = email;
        this.lostInfo = lostInfo;
    }

    public LostData() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getYourId() {
        return yourId;
    }

    public void setYourId(String yourId) {
        this.yourId = yourId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLostInfo() {
        return lostInfo;
    }

    public void setLostInfo(String lostInfo) {
        this.lostInfo = lostInfo;
    }
}
