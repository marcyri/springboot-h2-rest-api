package com.moan.pet.health_service.persistance.mapper;

import com.moan.pet.health_service.domain.model.Patient;
import com.moan.pet.health_service.persistance.entity.PatientEntity;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Mapper(componentModel = "spring", uses = {AttendanceMapStruct.class})
public abstract class PatientMapStruct {

    @Named("PatientIgnoreAttendanceChildPatients")
    @Mapping(target = "attendances", qualifiedByName = "AttendanceSetIgnorePatients") // attendances is a collection of complex objects, so we can't directly ignore its child properties as in the end, a Mapper needs to be defined to Map a single POJO into another
    public abstract Patient patientEntityToPatient(PatientEntity patientEntity);

    @Named("PatientListIgnoreAttendanceChildPatients")
    @IterableMapping(qualifiedByName = "PatientIgnoreAttendanceChildPatients")
    public abstract List<Patient> patientEntityToPatient(List<PatientEntity> patientEntities);

    // TODO: check if it is possible do it without implementation,
    // maybe not: https://github.com/mapstruct/mapstruct/issues/674
    public Optional<Patient> patientEntityToPatient(Optional<PatientEntity> patientEntity) {
        return patientEntity.map(this::patientEntityToPatient);
    }

    public abstract PatientEntity patientToPatientEntity(Patient patient);

    // for AttendanceMapStruct purpose:
    @Named("PatientIgnoreAttendances")
    @Mapping(target = "attendances", ignore = true)
    public abstract Patient patientEntityToPatientIgnoreAttendances(PatientEntity patientEntity);

    @Named("PatientSetIgnoreAttendances")
    @IterableMapping(qualifiedByName = "PatientIgnoreAttendances")
    public abstract Set<Patient> patientEntityToPatientIgnoreAttendances(Set<PatientEntity> patientEntities);

}
