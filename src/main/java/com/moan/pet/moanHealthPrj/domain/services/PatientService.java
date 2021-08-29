package com.moan.pet.moanHealthPrj.domain.services;

import com.moan.pet.moanHealthPrj.app.exception.NotFoundException;
import com.moan.pet.moanHealthPrj.app.exception.PatientNotFoundException;
import com.moan.pet.moanHealthPrj.app.mapper.PatientMapper;
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
    public List<Patient> getAll() {
        return patientRepository.findAll();
    }

    @Override
    public Patient getOneById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException(id));
    }

    @Override
    public List<Patient> findByAttendanceId(Long attendanceId) {
        return Optional.of(patientRepository.getPatientsByAttendanceId(attendanceId))
                .orElseThrow(() -> new NotFoundException("Patients for attendance with id " + attendanceId));
    }
}