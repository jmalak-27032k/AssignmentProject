package com.example.datastreaming.model;

import java.time.LocalDate;

public class User {
    private String name;
    private String address;
    private LocalDate dateOfBirth;
    // Getters and Setters


    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getName() {
        return name;
    }
}

