package com.ag04smarts.sha.controllers;

import com.ag04smarts.sha.errors.ApiError;
import com.ag04smarts.sha.errors.NotValidDateException;
import com.ag04smarts.sha.errors.PersonNotFoundException;
import com.ag04smarts.sha.services.AppointmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@Api(description = "This is appointment controller")
@RestController
@RequestMapping("/api/appointment")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(@Qualifier("appointmentService") AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @ApiOperation(value = "Create appointment between a patient and a doctor on some date")
    @PostMapping("/{patient_id}/{doctor_id}")
    public ResponseEntity createNewAppointment(@PathVariable("patient_id") Long patientId, @PathVariable("doctor_id") Long doctorId,
                                               @RequestParam("appointmentDate") String appointmentDate) throws NotValidDateException {
        return ResponseEntity.ok(appointmentService.createNewAppointment(patientId, doctorId, appointmentDate));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NotValidDateException.class)
    public ApiError handleDateError(ParseException ex) {
        return new ApiError(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler(PersonNotFoundException.class)
    public ApiError handlePersonNotFoundError(PersonNotFoundException ex) {
        return new ApiError(ex.getMessage(), HttpStatus.NOT_FOUND.value());
    }

}
