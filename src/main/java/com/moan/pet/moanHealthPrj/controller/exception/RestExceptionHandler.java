package com.moan.pet.moanHealthPrj.controller.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {NotFoundException.class,
            PatientNotFoundException.class, AttendanceNotFoundException.class})
    protected ResponseEntity<Object> handleNotFoundException(RuntimeException ex, WebRequest request) {
//        String bodyOfResponse = "Item not found";
        return handleExceptionInternal(ex, ex.getMessage(),
                new HttpHeaders(), HttpStatus.NO_CONTENT, request);
    }
}
