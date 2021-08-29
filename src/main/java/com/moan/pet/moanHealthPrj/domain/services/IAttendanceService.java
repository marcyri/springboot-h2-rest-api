package com.moan.pet.moanHealthPrj.domain.services;

import com.moan.pet.moanHealthPrj.domain.model.Attendance;

import java.util.List;

public interface IAttendanceService {
    List<Attendance> getAll();

    Attendance getOneById(Long id);

    List<Attendance> findByPatientId(Long id);
}
