package com.ag04smarts.sha.services;

import com.ag04smarts.sha.domain.Appointment;
import com.ag04smarts.sha.domain.Doctor;
import com.ag04smarts.sha.domain.Patient;
import com.ag04smarts.sha.errors.ApiError;
import com.ag04smarts.sha.errors.NotValidDateException;
import com.ag04smarts.sha.errors.PersonNotFoundException;
import com.ag04smarts.sha.repositories.AppointmentRepository;
import com.ag04smarts.sha.repositories.DoctorRepository;
import com.ag04smarts.sha.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;

@Service
@Qualifier("appointmentService")
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, PatientRepository patientRepository, DoctorRepository doctorRepository) {
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

    @Override
    public ResponseEntity createNewAppointment(Long patientId, Long doctorId, String appointmentDateString) throws NotValidDateException {

        //appointmentDateString in form '02-02-2021 14:00:00'
        appointmentDateString = appointmentDateString.replace("'", "");
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.UK);
        Date appointmentDate;

        try {
            appointmentDate = df.parse(appointmentDateString);
        } catch (ParseException e) {
            throw new NotValidDateException(e.getErrorOffset());
        }

        Optional<Patient> patient = patientRepository.findById(patientId);
        Optional<Doctor> doctor = doctorRepository.findById(doctorId);

        if (!patient.isPresent()) {
            throw new PersonNotFoundException(patientId);
        }

        if (!doctor.isPresent()) {
            throw new PersonNotFoundException(doctorId);
        }

        if (appointmentRepository.existsByPatientIdAndAppointmentDate(patientId, appointmentDate)) {
            return new ResponseEntity(
                new ApiError(
                    "Patient with ID  = " + patientId +
                " has already appointment on " + appointmentDate + "!",
                HttpStatus.BAD_REQUEST.value()),
                HttpStatus.BAD_REQUEST);
        }

        if (appointmentRepository.existsByDoctorIdAndAppointmentDate(doctorId, appointmentDate)) {
            return new ResponseEntity(
                new ApiError(
                    "Doctor with ID  = " + patientId +
                    " has already appointment on " + appointmentDate + "!",
                    HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
        }

        Appointment newAppointment = new Appointment(patient.get(), doctor.get(), appointmentDate);

        appointmentRepository.save(newAppointment);
        return new ResponseEntity<>("Appointment successfully created!", HttpStatus.OK);
    }
}
