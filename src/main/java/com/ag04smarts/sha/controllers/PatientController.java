package com.ag04smarts.sha.controllers;

import com.ag04smarts.sha.domain.Patient;
import com.ag04smarts.sha.errors.ApiError;
import com.ag04smarts.sha.errors.ImageNotSelectedException;
import com.ag04smarts.sha.errors.PersonNotFoundException;
import com.ag04smarts.sha.services.PatientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Api(description = "This is patient controller.")
@Slf4j
@RestController
@RequestMapping("/api/patient")
public class PatientController {

    private final PatientService patientService;

    public PatientController(@Qualifier("patientService") PatientService patientService) {
        this.patientService = patientService;
    }

    @ApiOperation(value = "View list of all patients")
    @GetMapping
    public List<Patient> getPatients() {
        return patientService.getAllPatients();
    }

    @ApiOperation(value = "Get patient by id")
    @GetMapping("/{id}")
    public Patient getOnePatient(@PathVariable("id") Long id) {
        return patientService.getPatientById(id);
    }

    @ApiOperation(value = "Save new patient")
    @PostMapping
    public Patient saveNewPatient(@RequestBody Patient patient) {
        return patientService.savePatient(patient);
    }

    @ApiOperation(value = "Delete patient")
    @DeleteMapping("/{id}")
    public ResponseEntity deletePatient(@PathVariable("id") Long id) {
        return patientService.deletePatient(id);
    }

    @ApiOperation(value = "Update patient")
    @PutMapping("/{id}")
    public Patient updatePatient(@RequestBody Patient newPatient, @PathVariable Long id) {
        return patientService.updatePatient(newPatient, id);
    }

    @ApiOperation(value = "Save image for patient")
    @PostMapping("/{patient_id}/image")
    public ResponseEntity handleImagePost(@PathVariable("patient_id") Long patientId, @RequestParam(value = "imageFile") MultipartFile file) {
        return ResponseEntity.ok(patientService.saveImageFile(patientId, file));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(PersonNotFoundException.class)
    public ApiError handlePersonNotFoundError(PersonNotFoundException ex) {
        return new ApiError(ex.getMessage(), HttpStatus.NOT_FOUND.value());
    }

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(ImageNotSelectedException.class)
    public ApiError handleImageNotSelectedError(ImageNotSelectedException ex) {
        return new ApiError(ex.getMessage(), HttpStatus.NOT_FOUND.value());
    }
}
