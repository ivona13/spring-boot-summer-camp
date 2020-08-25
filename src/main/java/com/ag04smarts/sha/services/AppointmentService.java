package com.ag04smarts.sha.services;

import com.ag04smarts.sha.errors.NotValidDateException;
import org.springframework.http.ResponseEntity;

public interface AppointmentService {
    ResponseEntity createNewAppointment(Long patientId, Long doctorId, String appointmentDateString) throws NotValidDateException;
}
