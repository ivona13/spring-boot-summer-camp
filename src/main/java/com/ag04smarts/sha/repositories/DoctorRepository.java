package com.ag04smarts.sha.repositories;

import com.ag04smarts.sha.domain.Doctor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepository extends CrudRepository<Doctor, Long> {
    Optional<Doctor> findById(Long id);

    Optional<Doctor> findByFirstNameAndLastName(String firstName, String lastName);
}
