package com.moan.pet.health_service.domain.service;

import com.moan.pet.health_service.domain.model.Attendance;
import com.moan.pet.health_service.domain.model.Patient;

import java.util.List;

public interface IPatientService {
    List<Patient> getAll();

    Patient getOneById(Long id);

    List<Patient> findByAttendanceId(Long id);

    Patient create(Patient patient);

    Patient update(Long id, Patient patient);
    Patient update(Long id, Attendance attendance);
    Patient update(Long patientId, Long attendanceId);
}
