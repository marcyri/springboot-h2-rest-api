package com.moan.pet.moanHealthPrj.model.entity;

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
@NamedEntityGraphs(value = {
        @NamedEntityGraph(name = "Attendance.patients", attributeNodes = {
                @NamedAttributeNode("patients")
        })
})
public class Attendance extends BaseEntity {

    @Lob
    private String reason;

    @Lob
    private String diagnosis;

    @ManyToMany(mappedBy = "attendances", fetch = FetchType.LAZY)
    private Set<Patient> patients = new HashSet<>();
}
