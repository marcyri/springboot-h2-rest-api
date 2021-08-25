package com.moan.pet.moanHealthPrj.app.exception;

public class AttendanceNotFoundException extends RuntimeException {

    public AttendanceNotFoundException(Long id) {
        super(String.format("Attendance with id %s not find", id));
    }
}
