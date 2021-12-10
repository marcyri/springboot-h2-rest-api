package com.moan.pet.health_service.persistance.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Entity
@Table(name = "Attendance")
public class AttendanceEntity extends BaseEntity {

    @Lob
    private String reason;

    @Lob
    private String diagnosis;

    @ManyToMany(mappedBy = "attendances", fetch = FetchType.LAZY)
    private Set<PatientEntity> patients = new HashSet<>();
}
