package com.ag04smarts.sha.domain;

import lombok.*;

import javax.persistence.*;
import javax.print.Doc;
import java.util.Date;

@Data
@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    private Doctor doctor;

    private Date appointmentDate;

    private Date lastModified;

    public Appointment() {
    }

    public Appointment(Patient patient, Doctor doctor, Date appointmentDate) {
        this.patient = patient;
        this.doctor = doctor;
        this.appointmentDate = appointmentDate;
    }

    @PrePersist
    public void putModificationDate() {
        putCurrentDate();
    }

    @PreUpdate
    public void updateModificationDate() {
        putCurrentDate();
    }

    private void putCurrentDate() {
        this.lastModified = new Date();
    }
}
