package com.moan.pet.health_service.persistance.mapper;

import com.moan.pet.health_service.domain.model.Patient;
import com.moan.pet.health_service.persistance.entity.PatientEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PatientMapper {
    private final PatientMapStruct mapper;

    public PatientMapper(PatientMapStruct mapper) {
        this.mapper = mapper;
    }

    public List<Patient> convert(List<PatientEntity> patientEntities) {
        return mapper.patientEntityToPatient(patientEntities);
    }

    public Optional<Patient> convert(Optional<PatientEntity> patientEntity) {
        return mapper.patientEntityToPatient(patientEntity);
    }

    public Patient convert(PatientEntity patientEntity) {
        return mapper.patientEntityToPatient(patientEntity);
    }

    public PatientEntity convert(Patient patient) {
        return mapper.patientToPatientEntity(patient);
    }
}
