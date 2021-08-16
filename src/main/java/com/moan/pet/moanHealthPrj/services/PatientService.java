package com.moan.pet.moanHealthPrj.services;

import com.cosium.spring.data.jpa.entity.graph.domain.EntityGraphs;
import com.moan.pet.moanHealthPrj.controller.exception.NotFoundException;
import com.moan.pet.moanHealthPrj.controller.exception.PatientNotFoundException;
import com.moan.pet.moanHealthPrj.model.dto.PatientDTO;
import com.moan.pet.moanHealthPrj.model.entity.Patient;
import com.moan.pet.moanHealthPrj.model.mapper.PatientMapper;
import com.moan.pet.moanHealthPrj.repositories.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PatientService implements IPatientService {
    PatientRepository repository;
    PatientMapper mapper;

    public PatientService(PatientRepository repository, PatientMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<PatientDTO> getAll() {
        // TODO: check when repository.findAll() returns empty nested entities
        return mapper.convert(repository.findAll());
    }

    @Override
    public List<PatientDTO> getAllWithNested() {
        List<Patient> result =
                StreamSupport.stream(repository.findAll(EntityGraphs.named("Patient.attendances")).spliterator(), false)
                        .collect(Collectors.toList());
        return mapper.convert(result);
    }

    @Override
    public PatientDTO getOneById(Long id) {
        Patient patient = repository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException(id));

        return mapper.convert(patient);
    }

    @Override
    public List<PatientDTO> findByAttendanceId(Long attendanceId) {
        List<Patient> patients = Optional.of(repository.findByAttendances_Id(attendanceId))
                .orElseThrow(() -> new NotFoundException("Patients for attendance with id " + attendanceId));

        return mapper.convert(patients);
    }
}