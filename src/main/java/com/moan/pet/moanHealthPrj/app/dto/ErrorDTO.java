package com.moan.pet.moanHealthPrj.app.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ErrorDTO {

    private HttpStatus status;
    private String errorMessage;

    public ErrorDTO(HttpStatus status, String errorMessage) {
        this.status = status;
        this.errorMessage = errorMessage;
    }
}
