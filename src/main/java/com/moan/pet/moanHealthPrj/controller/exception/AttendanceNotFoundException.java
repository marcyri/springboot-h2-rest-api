package com.moan.pet.moanHealthPrj.controller.exception;

public class AttendanceNotFoundException extends RuntimeException {

    public AttendanceNotFoundException(Long id) {
        super("Attendance not find " + id);
    }
}
