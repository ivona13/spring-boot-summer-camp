package com.ag04smarts.sha.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude = {"patient"})
@Entity
public class PatientMedicalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private Patient patient;

    @Lob
    private String diagnosis;

    @Lob
    private String treatment;

    @ManyToMany
    @JoinTable(name = "record_symptoms",
            joinColumns = @JoinColumn(name = "patient_record_id"), inverseJoinColumns = @JoinColumn(name = "symptom_id"))
    private Set<Symptom> symptoms;

}
