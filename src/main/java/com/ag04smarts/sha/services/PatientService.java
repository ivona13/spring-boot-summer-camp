package com.ag04smarts.sha.services;

import com.ag04smarts.sha.domain.Patient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PatientService {
    List<Patient> getAllPatients();

    Patient getPatientById(Long id);

    Patient savePatient(Patient patient);

    ResponseEntity deletePatient(Long id);

    Patient updatePatient(Patient newPatient, Long id);

    ResponseEntity saveImageFile(Long patientId, MultipartFile file);
}
