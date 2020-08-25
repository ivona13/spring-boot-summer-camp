package com.ag04smarts.sha.services;

import com.ag04smarts.sha.domain.Patient;
import com.ag04smarts.sha.errors.ImageNotSelectedException;
import com.ag04smarts.sha.errors.PersonNotFoundException;
import com.ag04smarts.sha.repositories.PatientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Qualifier("patientService")
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }


    @Override
    public List<Patient> getAllPatients() {
        log.info("I am in the patient service ...");
        List<Patient> patients = new ArrayList<>();
        patientRepository.findAll().forEach(patients::add);
        return patients;
    }

    @Override
    public Patient getPatientById(Long id) {
        log.debug("I am in the patient service ... method - getPatientById");
        return patientRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
    }

    @Override
    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public ResponseEntity deletePatient(Long id) {
        if (!patientRepository.existsById(id)) {
            throw new PersonNotFoundException(id);
        }

        patientRepository.deleteById(id);
        return new ResponseEntity("Patient with ID  = " + id + " is successfully deleted!", HttpStatus.OK);
    }

    @Override
    public Patient updatePatient(Patient newPatient, Long id) {

        Patient patientToBeUpdated = patientRepository.findById(id).orElse(null);
        if (patientToBeUpdated != null) {
            // patient with id exists, update its info
            newPatient.setId(id);
        }
        return patientRepository.save(newPatient);
    }

    @Override
    public ResponseEntity saveImageFile(Long patientId, MultipartFile file) {
        if (!patientRepository.existsById(patientId)) {
            throw new PersonNotFoundException(patientId);
        }

        if (file.isEmpty()) {
            throw new ImageNotSelectedException();
        }

        try {
            Patient patient = patientRepository.findById(patientId).get();
            Byte[] byteObjects = new Byte[file.getBytes().length];
            int i = 0;

            for (byte b : file.getBytes()) {
                byteObjects[i++] = b;
            }

            patient.setImage(byteObjects);
            patientRepository.save(patient);
        } catch (IOException e) {
            return new ResponseEntity("Error while getting bytes of the input file...", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Image saved!", HttpStatus.OK);
    }
}
