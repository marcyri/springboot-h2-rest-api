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
public abstract class PatientMapStruct {
//    PatientMapStruct INSTANCE = Mappers.getMapper(PatientMapStruct.class);

    @Autowired
    protected AttendanceMapStruct attendanceMapStruct;

    public abstract Patient patientEntityToPatient(PatientEntity patientEntity);
    public abstract List<Patient> patientEntityToPatient(List<PatientEntity> patientEntities);

    public abstract PatientEntity patientToPatientEntity(Patient patient);
    public abstract List<PatientEntity> patientToPatientEntity(List<Patient> patients);

    public Optional<Patient> patientEntityToPatient(Optional<PatientEntity> patientEntity) {
        return patientEntity.map(entity -> patientEntityToPatient(entity));
    }

    public abstract Set<Patient> patientEntityToPatient(Set<PatientEntity> patientEntities);

    public Attendance attendanceEntityToAttendance(AttendanceEntity attendanceEntity) {
        return attendanceMapStruct.attendanceEntityToAttendance(attendanceEntity);
    }

    public Set<Attendance> attendanceEntityToAttendance(Set<AttendanceEntity> attendanceEntities) {
        return attendanceMapStruct.attendanceEntityToAttendance(attendanceEntities);
    }
}
