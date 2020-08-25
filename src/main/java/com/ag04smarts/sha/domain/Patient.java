package com.ag04smarts.sha.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Slf4j
@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Patient extends Person {

    @ApiModelProperty(value = "Email of a patient", required = true)
    private String email;

    @ApiModelProperty(value = "Age of a patient", required = true)
    private Integer age;

    @ApiModelProperty(value = "Phone number of a patient", required = true)
    private String phoneNumber;

    @ApiModelProperty(value = "Gender of a patient", required = true)
    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @ApiModelProperty(value = "Enlistment date of a patient", required = true)
    private Date enlistmentDate;

    @ApiModelProperty(hidden = true)
    private Date createdDate;

    @ApiModelProperty(hidden = true)
    private Date updatedDate;

    @ApiModelProperty(value = "Status of a patient", required = true)
    @Enumerated(value = EnumType.STRING)
    private Status status;

    @ApiModelProperty(hidden = true)
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private PatientMedicalRecord patientMedicalRecord;

    @ApiModelProperty(hidden = true)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "patient")
    @JsonIgnore
    private Set<PatientTreatmentHistory> patientTreatmentHistories;

    @ApiModelProperty(hidden = true)
    @Lob
    private Byte[] image;

    public Patient() {
    }

    public Patient(String firstName, String lastName, String email, Integer age, String phoneNumber, Gender gender,
                   Date enlistmentDate, Status status) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.enlistmentDate = enlistmentDate;
        this.status = status;
    }

    public void setPatientMedicalRecord(PatientMedicalRecord patientMedicalRecord) {
        this.patientMedicalRecord = patientMedicalRecord;
        patientMedicalRecord.setPatient(this);
    }

    public void setPatientTreatmentHistories(Set<PatientTreatmentHistory> patientTreatmentHistories) {
        this.patientTreatmentHistories = patientTreatmentHistories;
        for (PatientTreatmentHistory patientTreatmentHistory : patientTreatmentHistories) {
            patientTreatmentHistory.setPatient(this);
        }
    }

    @Override
    public String toString() {
        return "Patient(firstName=" + getFirstName() + ", lastName=" + getLastName() + ", email=" + email + ", age=" + age + ", phoneNumber="
                + phoneNumber + ", gender=" + gender + ", enlistmentDate=" + enlistmentDate + ", status=" + status + ", patientMedicalRecord="
                + patientMedicalRecord.toString() + ")";
    }

    @PrePersist
    public void createNewPatient() {
        createdDate = new Date();
    }

    @PreUpdate
    public void updateExistingPatient() {
        updatedDate = new Date();
    }
}
