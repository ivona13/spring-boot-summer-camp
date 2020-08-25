package com.ag04smarts.sha.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Data
@EqualsAndHashCode(exclude = {"patient"})
@Entity
public class PatientTreatmentHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    private Doctor doctor;

    @Lob
    private String treatmentRemark;

    @Enumerated(value = EnumType.STRING)
    private Status oldStatus;

    @Enumerated(value = EnumType.STRING)
    private Status newStatus;
}
