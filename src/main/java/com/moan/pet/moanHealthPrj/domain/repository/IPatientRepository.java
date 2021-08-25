package com.moan.pet.moanHealthPrj.domain.repository;

import com.moan.pet.moanHealthPrj.domain.model.Patient;

import java.util.List;
import java.util.Optional;

public interface IPatientRepository {
    List<Patient> findAll();

    List<Patient> findAllWithNestedAttendances();

    Optional<Patient> findById(Long id);

    List<Patient> getPatientsByAttendanceId(Long attendanceId);
}
