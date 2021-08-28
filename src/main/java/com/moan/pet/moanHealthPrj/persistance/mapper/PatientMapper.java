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

    public List<Patient> convert(List<PatientEntity> patientEntities) {
        return mapper.patientEntityToPatient(patientEntities);
    }

    public Optional<Patient> convert(Optional<PatientEntity> patientEntity) {
        return mapper.patientEntityToPatient(patientEntity);
    }

    // TODO: TBD
    public PatientEntity convert(Patient patient) {
        return null;
    }
}
