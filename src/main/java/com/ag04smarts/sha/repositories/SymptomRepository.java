package com.ag04smarts.sha.repositories;

import com.ag04smarts.sha.domain.Symptom;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SymptomRepository extends CrudRepository<Symptom, Long> {
    Optional<Symptom> findByDescription(String description);
}
