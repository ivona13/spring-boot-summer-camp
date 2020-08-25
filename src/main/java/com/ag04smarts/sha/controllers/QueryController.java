package com.ag04smarts.sha.controllers;

import com.ag04smarts.sha.domain.Patient;
import com.ag04smarts.sha.repositories.PatientRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Api(description = "This is query controller.")
@RestController
@RequestMapping("/api/query")
public class QueryController {

    private final PatientRepository patientRepository;

    public QueryController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @ApiOperation(value = "Get patients older than 21 who were enlisted after 01-01-2020")
    @GetMapping("/1")
    public Iterable<Patient> getPatientsOlderThan21WithEnlistmentDateIn2020() throws ParseException {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.UK);
        return patientRepository.findAllByAgeGreaterThanAndEnlistmentDateAfter(21, df.parse("01/01/2020 00:00:00"));
    }

    @ApiOperation(value = "Get patients whose symptoms include either coughing or fever symptom")
    @GetMapping("/2")
    public Iterable<Patient> getMedicalRecordsWithSymptomsCoughingAndFever() {
        Set<String> symptomDescriptions = new HashSet<String>(Arrays.asList("fever", "coughing"));
        return patientRepository.findDistinctByPatientMedicalRecordSymptomsDescriptionIn(symptomDescriptions);
    }
}
