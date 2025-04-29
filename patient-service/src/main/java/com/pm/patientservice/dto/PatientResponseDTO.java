package com.pm.patientservice.dto;

import java.util.UUID;

public class PatientResponseDTO {

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String birthDate) {
        this.dateOfBirth = birthDate;
    }

    private UUID id;
    private String name;
    private String email;
    private String address;
    private String dateOfBirth;
}
