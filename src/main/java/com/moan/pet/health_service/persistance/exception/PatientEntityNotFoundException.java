package com.moan.pet.health_service.persistance.exception;

public class PatientEntityNotFoundException extends RuntimeException {

    public PatientEntityNotFoundException(Long id) {
        super(String.format("Patient entity with id %s not find", id));
    }
}
