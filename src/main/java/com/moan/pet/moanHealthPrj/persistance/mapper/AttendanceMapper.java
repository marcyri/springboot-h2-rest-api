package com.moan.pet.moanHealthPrj.persistance.mapper;


import com.moan.pet.moanHealthPrj.domain.model.Attendance;
import com.moan.pet.moanHealthPrj.domain.model.Patient;
import com.moan.pet.moanHealthPrj.persistance.entity.AttendanceEntity;
import com.moan.pet.moanHealthPrj.persistance.entity.PatientEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class AttendanceMapper {
    private final ModelMapper modelMapper;

    public AttendanceMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Attendance convert(AttendanceEntity attendanceEntity) {
        return modelMapper.map(attendanceEntity, Attendance.class);
    }

    public AttendanceEntity convert(Attendance attendance) {
        return modelMapper.map(attendance, AttendanceEntity.class);
    }

    public List<Attendance> convert(List<AttendanceEntity> attendances) {
        return attendances.stream()
                .map(attendance -> {
                    Attendance curAttendance = convert(attendance);
                    curAttendance.setPatients(convert(attendance.getPatients()));
                    return curAttendance;
                })
                .collect(Collectors.toList());
    }

    public Set<Patient> convert(Set<PatientEntity> patients) {
        return modelMapper.map(patients, new TypeToken<Set<Patient>>() {}.getType());
    }
}
