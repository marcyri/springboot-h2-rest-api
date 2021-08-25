package com.moan.pet.moanHealthPrj.app.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String msg) {
        super(msg + " not find");
    }
}
