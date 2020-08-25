package com.ag04smarts.sha.controllers;

import com.ag04smarts.sha.domain.Gender;
import com.ag04smarts.sha.domain.Patient;
import com.ag04smarts.sha.domain.Status;
import com.ag04smarts.sha.services.PatientService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class PatientControllerTest {

    @Mock
    private PatientService patientService;

    private PatientController controller;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        controller = new PatientController(patientService);
    }

    @Test
    public void testMockMVC() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(get("/api/patient"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetMapping() throws Exception {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.UK);

        List<Patient> patientsData = new ArrayList<>();

        Patient patientAnaTomic = new Patient("Ana", "Tomic", "anatomic@gmail.com", 26, "0919567888", Gender.FEMALE,
                df.parse("01/01/2018 10:00:00"), Status.ENLISTED);

        Patient patientMarkoMamic = new Patient("Marko", "Mamic", "markomamic@gmail.com", 45, "0912222444", Gender.MALE,
                df.parse("02/03/2020 12:00:00"), Status.CURED);

        patientsData.add(patientAnaTomic);
        patientsData.add(patientMarkoMamic);

        when(patientService.getAllPatients()).thenReturn(patientsData);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(get("/api/patient"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].firstName").value("Ana"))
                .andExpect(jsonPath("$[0].lastName").value("Tomic"));
    }
}
