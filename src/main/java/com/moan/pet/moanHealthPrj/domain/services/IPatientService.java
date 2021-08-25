package com.moan.pet.moanHealthPrj.domain.services;

import com.moan.pet.moanHealthPrj.app.dto.PatientDTO;

import java.util.List;

public interface IPatientService {
    List<PatientDTO> getAll();

    List<PatientDTO> getAllWithNested();

    PatientDTO getOneById(Long id);

    List<PatientDTO> findByAttendanceId(Long id);
}
