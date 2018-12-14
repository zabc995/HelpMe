package com.gethelp.huyngh.model;

public class Account {
    String accountId;
    Character phoneNumber;
    Character password;
    String firstName;
    String lastName;
    Boolean Gender;
    String Mail;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

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

    public Account() {
    }

    public Account(String accountId, Character phoneNumber, Character password, String firstName, String lastName, Boolean gender, String mail) {
        this.accountId = accountId;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        Gender = gender;
        Mail = mail;
    }
}
