package com.ag04smarts.sha.controllers;

import com.ag04smarts.sha.errors.ApiError;
import com.ag04smarts.sha.forms.EnlistmentForm;
import com.ag04smarts.sha.services.EnlistmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(description = "This is enlistment controller.")
@RestController
@RequestMapping("/api/patient/enlistment")
public class EnlistmentController {
    private final EnlistmentService enlistmentService;

    public EnlistmentController(@Qualifier("enlistmentService") EnlistmentService enlistmentService) {
        this.enlistmentService = enlistmentService;
    }

    @ApiOperation(value = "Enlist new patient")
    @PostMapping
    public ResponseEntity saveNewPatient(@Valid @RequestBody EnlistmentForm enlistmentForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(new ApiError(bindingResult.getFieldErrors()), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(enlistmentService.saveNewPatientByEnlistmentForm(enlistmentForm));
    }
}
