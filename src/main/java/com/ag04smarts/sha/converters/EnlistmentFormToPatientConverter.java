package com.ag04smarts.sha.converters;

import com.ag04smarts.sha.domain.Patient;
import com.ag04smarts.sha.domain.Status;
import com.ag04smarts.sha.forms.EnlistmentForm;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import lombok.Synchronized;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class EnlistmentFormToPatientConverter implements Converter<EnlistmentForm, Patient> {

    @Synchronized
    @Nullable
    @Override
    public Patient convert(EnlistmentForm source) {
        if (source == null) {
            return null;
        }
        return new Patient(source.getFirstName(), source.getLastName(), source.getEmail(), source.getAge(), source.getPhoneNumber(),
                source.getGender(), new Date(), Status.ENLISTED);
    }

    @Override
    public JavaType getInputType(TypeFactory typeFactory) {
        return typeFactory.constructType(EnlistmentForm.class);
    }

    @Override
    public JavaType getOutputType(TypeFactory typeFactory) {
        return typeFactory.constructType(Patient.class);
    }
}
