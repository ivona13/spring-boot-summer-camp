package com.ag04smarts.sha.services;

import com.ag04smarts.sha.converters.EnlistmentFormToPatientConverter;
import com.ag04smarts.sha.domain.Patient;
import com.ag04smarts.sha.forms.EnlistmentForm;
import com.ag04smarts.sha.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
@Qualifier("enlistmentService")
public class EnlistmentServiceImpl implements EnlistmentService {
    private final PatientRepository patientRepository;
    private final EnlistmentFormToPatientConverter enlistmentFormToPatientConverter;


    public EnlistmentServiceImpl(PatientRepository patientRepository, EnlistmentFormToPatientConverter enlistmentFormToPatientConverter) {
        this.patientRepository = patientRepository;
        this.enlistmentFormToPatientConverter = enlistmentFormToPatientConverter;
    }

    @Override
    public Patient saveNewPatientByEnlistmentForm(EnlistmentForm enlistmentForm) {
        Patient patient = enlistmentFormToPatientConverter.convert(enlistmentForm);
        patientRepository.save(patient);
        return patient;
    }
}

