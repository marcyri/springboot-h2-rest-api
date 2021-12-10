package com.moan.pet.health_service.domain.service;

import com.moan.pet.health_service.domain.model.Attendance;

import java.util.List;

public interface IAttendanceService {
    List<Attendance> getAll();

    Attendance getOneById(Long id);

    List<Attendance> findByPatientId(Long id);

    Attendance create(Attendance attendance);
}
