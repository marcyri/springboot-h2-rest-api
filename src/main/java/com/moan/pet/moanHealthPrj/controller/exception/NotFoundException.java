package com.moan.pet.moanHealthPrj.controller.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String msg) {
        super(msg + " not find");
    }
}
