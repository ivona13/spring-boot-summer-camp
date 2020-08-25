package com.ag04smarts.sha.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class Doctor extends Person {

    @Enumerated(value = EnumType.STRING)
    private DoctorExpertise doctorExpertise;

    public Doctor() {
    }

    public Doctor(String firstName, String lastName, DoctorExpertise doctorExpertise) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.doctorExpertise = doctorExpertise;
    }

    @Override
    public String toString() {
        return "Doctor(firstName=" + getFirstName() + ", lastName=" + getLastName() + ", doctorExpertise=" + doctorExpertise;
    }
}

