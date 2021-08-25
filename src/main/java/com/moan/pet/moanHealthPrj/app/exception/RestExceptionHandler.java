package com.moan.pet.moanHealthPrj.app.exception;

import com.moan.pet.moanHealthPrj.app.dto.ErrorDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value
            = { NotFoundException.class, PatientNotFoundException.class, AttendanceNotFoundException.class })
    protected ResponseEntity<Object> handleNotFoundException(RuntimeException ex, WebRequest request) {
        ErrorDTO error = new ErrorDTO(HttpStatus.NOT_FOUND, ex.getMessage());
        return handleExceptionInternal(ex, error,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}