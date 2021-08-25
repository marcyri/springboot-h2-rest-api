package com.moan.pet.moanHealthPrj.persistance.mapper;

import com.moan.pet.moanHealthPrj.domain.model.Patient;
import com.moan.pet.moanHealthPrj.persistance.entity.PatientEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PatientMapper {
    private final PatientMapStruct mapper;

    public PatientMapper(PatientMapStruct mapper) {
        this.mapper = mapper;
    }

    public Patient convert(PatientEntity patientEntity) {
        return mapper.patientEntityToPatient(patientEntity);
    }

    public PatientEntity convert(Patient patient) {
        return mapper.patientToPatientEntity(patient);
    }

    public List<Patient> convert(List<PatientEntity> patientEntities) {
        return mapper.patientEntityToPatient(patientEntities);
//        return null;
//        return patients.stream()
//                .map(patient -> {
//                    Patient curPatient = convert(patient);
//                    curPatient.setAttendances(convert(patient.getAttendances()));
//                    return curPatient;
//                })
//                .collect(Collectors.toList());
    }

    public Optional<Patient> convert(Optional<PatientEntity> patientEntity) {
//        return patientEntity.map(entity -> {
//            Patient patient = convert(entity);
//            patient.setAttendances(convert(entity.getAttendances()));
//            return patient;
//        });
//        return Optional.empty();
        return mapper.patientEntityToPatient(patientEntity);
    }
}
