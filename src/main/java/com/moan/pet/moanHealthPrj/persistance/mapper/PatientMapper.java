package com.moan.pet.moanHealthPrj.persistance.mapper;


import com.moan.pet.moanHealthPrj.domain.model.Attendance;
import com.moan.pet.moanHealthPrj.domain.model.Patient;
import com.moan.pet.moanHealthPrj.persistance.entity.AttendanceEntity;
import com.moan.pet.moanHealthPrj.persistance.entity.PatientEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PatientMapper {
    private final ModelMapper modelMapper;

    public PatientMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Patient convert(PatientEntity patientEntity) {
        return modelMapper.map(patientEntity, Patient.class);
    }

    public PatientEntity convert(Patient patient) {
        return modelMapper.map(patient, PatientEntity.class);
    }

    public List<Patient> convert(List<PatientEntity> patients) {
        return patients.stream()
                .map(patient -> {
                    Patient curPatient = convert(patient);
                    curPatient.setAttendances(convert(patient.getAttendances()));
                    return curPatient;
                })
                .collect(Collectors.toList());
    }

    public Optional<Patient> convert(Optional<PatientEntity> patientEntity) {
        return patientEntity.map(entity -> {
            Patient patient = convert(entity);
            patient.setAttendances(convert(entity.getAttendances()));
            return patient;
        });
    }

    public Set<Attendance> convert(Set<AttendanceEntity> attendances) {
        return modelMapper.map(attendances, new TypeToken<Set<Attendance>>() {
        }.getType());
    }
}
