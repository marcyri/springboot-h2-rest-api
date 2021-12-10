package com.moan.pet.health_service.domain.repository;

import com.moan.pet.health_service.domain.model.Attendance;
import com.moan.pet.health_service.domain.model.Patient;

import java.util.List;
import java.util.Optional;

public interface IPatientRepository {
    List<Patient> findAll();

    Optional<Patient> findById(Long id);

    List<Patient> getPatientsByAttendanceId(Long attendanceId);

    Patient create(Patient patient);

    Patient update(Long id, Patient patient);
    Patient update(Long id, Attendance attendance);
}
