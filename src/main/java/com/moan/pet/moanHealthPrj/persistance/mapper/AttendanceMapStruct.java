package com.moan.pet.moanHealthPrj.persistance.mapper;

import com.moan.pet.moanHealthPrj.domain.model.Attendance;
import com.moan.pet.moanHealthPrj.persistance.entity.AttendanceEntity;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Mapper(componentModel = "spring", uses = {PatientMapStruct.class})
public abstract class AttendanceMapStruct {
    @Named("AttendanceIgnorePatientChildAttendances")
    @Mapping(target = "patients", qualifiedByName = "PatientSetIgnoreAttendances")
    public abstract Attendance attendanceEntityToAttendance(AttendanceEntity attendanceEntity);

    @Named("AttendanceListIgnorePatientChildAttendances")
    @IterableMapping(qualifiedByName = "AttendanceIgnorePatientChildAttendances")
    public abstract List<Attendance> attendanceEntityToAttendance(List<AttendanceEntity> attendanceEntities);

    public Optional<Attendance> attendanceEntityToAttendance(Optional<AttendanceEntity> attendanceEntity) {
        return attendanceEntity.map(this::attendanceEntityToAttendance);
    }

    public abstract AttendanceEntity attendanceToAttendanceEntity(Attendance attendance);

    // for PatientMapStruct purpose:
    @Named("AttendanceIgnorePatients")
    @Mapping(target = "patients", ignore = true) // ignore the property - patients
    public abstract Attendance attendanceEntityToAttendanceIgnorePatients(AttendanceEntity attendanceEntity);

    @Named("AttendanceSetIgnorePatients")
    @IterableMapping(qualifiedByName = "AttendanceIgnorePatients")
    public abstract Set<Attendance> attendanceEntityToAttendanceIgnorePatients(Set<AttendanceEntity> attendanceEntities);
}
