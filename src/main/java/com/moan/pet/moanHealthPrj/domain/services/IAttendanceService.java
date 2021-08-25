package com.moan.pet.moanHealthPrj.domain.services;

import com.moan.pet.moanHealthPrj.app.dto.AttendanceDTO;

import java.util.List;

public interface IAttendanceService {
    List<AttendanceDTO> getAll();

    List<AttendanceDTO> getAllWithNested();

    AttendanceDTO getOneById(Long id);

    List<AttendanceDTO> findByPatientId(Long id);
}
