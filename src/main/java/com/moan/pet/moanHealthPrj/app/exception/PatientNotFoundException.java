package com.moan.pet.moanHealthPrj.app.exception;

public class PatientNotFoundException extends RuntimeException {

    public PatientNotFoundException(Long id) {
        super(String.format("Patient with id %s not find", id));
    }
}
