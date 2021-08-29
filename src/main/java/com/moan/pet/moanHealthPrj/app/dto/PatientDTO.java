package com.moan.pet.moanHealthPrj.app.dto;

import com.moan.pet.moanHealthPrj.persistance.entity.Gender;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Data
public class PatientDTO extends RepresentationModel<PatientDTO> {
    private Long id;

    private LocalDateTime createdDtTm;

    private LocalDateTime updatedDtTm;

    private String familyName;

    private String name;

    private Gender gender;

    private Date dateOfBirth;

    Set<AttendanceDTO> attendances;
}
