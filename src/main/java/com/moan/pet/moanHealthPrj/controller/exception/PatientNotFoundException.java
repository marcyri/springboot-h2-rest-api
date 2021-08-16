package com.moan.pet.moanHealthPrj.controller.exception;

public class PatientNotFoundException extends RuntimeException {

    public PatientNotFoundException(Long id) {
        super("Patient not find " + id);
    }
}
