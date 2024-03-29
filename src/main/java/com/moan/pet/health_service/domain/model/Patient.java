package com.moan.pet.health_service.domain.model;

import com.moan.pet.health_service.persistance.entity.Gender;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Patient extends BaseDomainModel {
    private String familyName;

    private String name;

    private Gender gender;

    private Date dateOfBirth;

    private Set<Attendance> attendances;
}
