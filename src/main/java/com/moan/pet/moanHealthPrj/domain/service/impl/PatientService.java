package com.moan.pet.moanHealthPrj.domain.service.impl;

import com.moan.pet.moanHealthPrj.app.exception.NotFoundException;
import com.moan.pet.moanHealthPrj.app.exception.PatientNotFoundException;
import com.moan.pet.moanHealthPrj.domain.model.Attendance;
import com.moan.pet.moanHealthPrj.domain.model.Patient;
import com.moan.pet.moanHealthPrj.domain.repository.IAttendanceRepository;
import com.moan.pet.moanHealthPrj.domain.repository.IPatientRepository;
import com.moan.pet.moanHealthPrj.domain.service.IPatientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PatientService implements IPatientService {
    private final IPatientRepository patientRepository;
    private final IAttendanceRepository attendanceRepository;

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

    @Override
    public Patient create(Patient patient) {
        return patientRepository.create(patient);
    }

    public Patient update(Long id, Patient patient) {
        return patientRepository.update(id, patient);
    }
    public Patient update(Long id, Attendance attendance) {
        return patientRepository.update(id, attendance);
    }

    @Override
    public Patient update(Long patientId, Long attendanceId) {
        Attendance attendance = attendanceRepository.getById(attendanceId);
        return patientRepository.update(patientId, attendance);
    }
}