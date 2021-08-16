package com.moan.pet.moanHealthPrj.services;

import com.moan.pet.moanHealthPrj.model.dto.PatientDTO;

import java.util.List;

public interface IPatientService {
    List<PatientDTO> getAll();

    List<PatientDTO> getAllWithNested();

    PatientDTO getOneById(Long id);

    List<PatientDTO> findByAttendanceId(Long id);
}
