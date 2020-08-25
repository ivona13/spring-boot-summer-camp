package com.ag04smarts.sha.repositories;

import com.ag04smarts.sha.domain.Patient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

@Repository
public interface PatientRepository extends CrudRepository<Patient, Long> {
    Optional<Patient> findById(Long id);

    Iterable<Patient> findAllByAgeGreaterThanAndEnlistmentDateAfter(Integer age, Date date);

    Iterable<Patient> findDistinctByPatientMedicalRecordSymptomsDescriptionIn(Set<String> symptoms);
}
