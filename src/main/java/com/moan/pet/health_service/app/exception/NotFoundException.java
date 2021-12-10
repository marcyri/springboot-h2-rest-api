package com.moan.pet.health_service.app.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String msg) {
        super(msg + " not find");
    }
}
