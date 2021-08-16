package com.moan.pet.moanHealthPrj.services;

import com.moan.pet.moanHealthPrj.model.dto.AttendanceDTO;

import java.util.List;

public interface IAttendanceService {
    List<AttendanceDTO> getAll();

    List<AttendanceDTO> getAllWithNested();

    AttendanceDTO getOneById(Long id);

    List<AttendanceDTO> findByPatientId(Long id);
}
