package com.moan.pet.moanHealthPrj.app.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class AttendanceDTO {
    private Long id;

    private LocalDateTime createdDtTm;

    private LocalDateTime updatedDtTm;

    private String reason;

    private String diagnosis;

    private Set<PatientDTO> patients;
}
