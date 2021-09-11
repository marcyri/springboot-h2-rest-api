package com.moan.pet.moanHealthPrj.domain.service;

import com.moan.pet.moanHealthPrj.domain.model.Patient;

import java.util.List;

public interface IPatientService {
    List<Patient> getAll();

    Patient getOneById(Long id);

    List<Patient> findByAttendanceId(Long id);

    Patient create(Patient patient);
}
