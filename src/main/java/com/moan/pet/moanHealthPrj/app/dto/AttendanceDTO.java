package com.moan.pet.moanHealthPrj.app.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class AttendanceDTO extends RepresentationModel<AttendanceDTO> {
    private Long id;

    private LocalDateTime createdDtTm;

    private LocalDateTime updatedDtTm;

    private String reason;

    private String diagnosis;

    private Set<PatientDTO> patients;
}
