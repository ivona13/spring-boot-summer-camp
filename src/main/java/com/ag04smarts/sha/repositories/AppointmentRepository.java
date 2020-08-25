package com.ag04smarts.sha.repositories;

import com.ag04smarts.sha.domain.Appointment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface AppointmentRepository extends CrudRepository<Appointment, Long> {
    boolean existsByPatientIdAndAppointmentDate(Long patientId, Date appointmentDate);

    boolean existsByDoctorIdAndAppointmentDate(Long doctorId, Date appointmentDate);
}