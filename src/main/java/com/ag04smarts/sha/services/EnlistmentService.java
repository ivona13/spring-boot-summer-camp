package com.ag04smarts.sha.services;

import com.ag04smarts.sha.domain.Patient;
import com.ag04smarts.sha.forms.EnlistmentForm;
import org.springframework.http.ResponseEntity;

public interface EnlistmentService {
    Patient saveNewPatientByEnlistmentForm(EnlistmentForm enlistmentForm);
}
