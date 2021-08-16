package com.moan.pet.moanHealthPrj.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Entity
@NamedEntityGraphs(value = {
        @NamedEntityGraph(name = "Patient.attendances", attributeNodes = {
                @NamedAttributeNode("attendances")
        })
})
public class Patient extends BaseEntity {

    @Column
    private String familyName;

    @Column
    private String name;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column
    private Date dateOfBirth;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "patients_attendances",
            joinColumns = @JoinColumn(name = "patient_id"),
            inverseJoinColumns = @JoinColumn(name = "attendance_id"))
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Attendance> attendances;
}
