package com.moan.pet.health_service.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Attendance extends BaseDomainModel {
    private String reason;

    private String diagnosis;

    private Set<Patient> patients = new HashSet<>();
}
