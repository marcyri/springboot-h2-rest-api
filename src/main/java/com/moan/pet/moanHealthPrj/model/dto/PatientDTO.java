package com.moan.pet.moanHealthPrj.model.dto;

import com.moan.pet.moanHealthPrj.model.entity.Gender;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
public class PatientDTO extends RepresentationModel<PatientDTO> {
    private Long id;

    private LocalDateTime createdDtTm;

    private LocalDateTime updatedDtTm;

    private String familyName;

    private String name;

    private Gender gender;

    private Date dateOfBirth;

    Set<NestedAttendanceDTO> attendances;
}
