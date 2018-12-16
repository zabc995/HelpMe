package com.gethelp.huyngh.model;

public class Account {
    Character phoneNumber;
    Character password;
    String firstName;
    String lastName;
    String address;
    Boolean Gender;
    String Mail;

    public Character getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Character phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Character getPassword() {
        return password;
    }

    public void setPassword(Character password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Boolean getGender() {
        return Gender;
    }

    public void setGender(Boolean gender) {
        Gender = gender;
    }

    public String getMail() {
        return Mail;
    }

    public void setMail(String mail) {
        Mail = mail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Account() {
    }

    public Account(Character phoneNumber, Character password, String firstName, String lastName, String address, Boolean gender, String mail) {
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        Gender = gender;
        Mail = mail;
    }
}
