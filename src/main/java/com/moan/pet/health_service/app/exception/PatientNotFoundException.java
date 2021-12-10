package com.moan.pet.health_service.app.exception;

public class PatientNotFoundException extends RuntimeException {

    public PatientNotFoundException(Long id) {
        super(String.format("Patient with id %s not find", id));
    }
}
