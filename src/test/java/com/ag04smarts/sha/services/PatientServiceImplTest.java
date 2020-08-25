package com.ag04smarts.sha.services;

import com.ag04smarts.sha.converters.EnlistmentFormToPatientConverter;
import com.ag04smarts.sha.domain.Gender;
import com.ag04smarts.sha.domain.Patient;
import com.ag04smarts.sha.domain.Status;
import com.ag04smarts.sha.repositories.PatientRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class PatientServiceImplTest {

    private PatientServiceImpl patientService;

    @Mock
    private PatientRepository patientRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        patientService = new PatientServiceImpl(patientRepository);
    }

    @Test
    public void getAllPatients() throws ParseException {

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.UK);

        List<Patient> patientsData = new ArrayList<>();

        Patient patientAnaTomic = new Patient("Ana", "Tomic", "anatomic@gmail.com", 26, "0919567888", Gender.FEMALE,
                df.parse("01/01/2018 10:00:00"), Status.ENLISTED);

        Patient patientMarkoMamic = new Patient("Marko", "Mamic", "markomamic@gmail.com", 45, "0912222444", Gender.MALE,
                df.parse("02/03/2020 12:00:00"), Status.CURED);

        patientsData.add(patientAnaTomic);
        patientsData.add(patientMarkoMamic);

        when(patientRepository.findAll()).thenReturn(patientsData);

        List<Patient> patients = patientService.getAllPatients();

        assertEquals(patients.size(), 2);
        verify(patientRepository, times(1)).findAll();
    }
}