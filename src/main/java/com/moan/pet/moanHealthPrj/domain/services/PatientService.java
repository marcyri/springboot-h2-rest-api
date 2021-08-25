package com.moan.pet.moanHealthPrj.domain.services;

import com.moan.pet.moanHealthPrj.app.dto.PatientDTO;
import com.moan.pet.moanHealthPrj.app.exception.NotFoundException;
import com.moan.pet.moanHealthPrj.app.exception.PatientNotFoundException;
import com.moan.pet.moanHealthPrj.domain.mapper.PatientMapper;
import com.moan.pet.moanHealthPrj.domain.model.Patient;
import com.moan.pet.moanHealthPrj.domain.repository.IPatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService implements IPatientService {
    IPatientRepository patientRepository;
    PatientMapper patientMapper;

    public PatientService(IPatientRepository patientRepository, PatientMapper patientMapper) {
        this.patientRepository = patientRepository;
        this.patientMapper = patientMapper;
    }

    @Override
    public List<PatientDTO> getAll() {
        // TODO: check when repository.findAll() returns empty nested entities
        return patientMapper.convert(patientRepository.findAll());
    }

    @Override
    public List<PatientDTO> getAllWithNested() {
        return patientMapper.convert(patientRepository.findAllWithNestedAttendances());
    }

    @Override
    public PatientDTO getOneById(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException(id));
        return patientMapper.convert(patient);
    }

    @Override
    public List<PatientDTO> findByAttendanceId(Long attendanceId) {
        List<Patient> patients = Optional.of(patientRepository.getPatientsByAttendanceId(attendanceId))
                .orElseThrow(() -> new NotFoundException("Patients for attendance with id " + attendanceId));

        return patientMapper.convert(patients);
    }
}