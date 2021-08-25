package com.moan.pet.moanHealthPrj.persistance.mapper;

import com.moan.pet.moanHealthPrj.domain.model.Attendance;
import com.moan.pet.moanHealthPrj.domain.model.Patient;
import com.moan.pet.moanHealthPrj.persistance.entity.AttendanceEntity;
import com.moan.pet.moanHealthPrj.persistance.entity.PatientEntity;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Mapper(componentModel = "spring")
public abstract class AttendanceMapStruct {

    @Autowired
    protected PatientMapStruct patientMapStruct;

    public abstract Attendance attendanceEntityToAttendance(AttendanceEntity attendanceEntity);

    public abstract List<Attendance> attendanceEntityToAttendance(List<AttendanceEntity> attendanceEntities);

    public abstract AttendanceEntity attendanceToAttendanceEntity(Attendance attendance);

    public abstract List<AttendanceEntity> attendanceToAttendanceEntity(List<Attendance> attendances);

    public Optional<Attendance> attendanceEntityToAttendance(Optional<AttendanceEntity> attendanceEntity) {
        return attendanceEntity.map(this::attendanceEntityToAttendance);
    }

    public abstract Set<Attendance> attendanceEntityToAttendance(Set<AttendanceEntity> attendanceEntities);

    protected Patient patientEntityToPatient(PatientEntity patientEntity) {
        return patientMapStruct.patientEntityToPatient(patientEntity);
    }

    protected Set<Patient> patientEntityToPatient(Set<PatientEntity> patientEntities) {
        return patientMapStruct.patientEntityToPatient(patientEntities);
    }
}
