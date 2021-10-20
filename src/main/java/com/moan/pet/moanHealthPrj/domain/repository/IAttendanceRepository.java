package com.moan.pet.moanHealthPrj.domain.repository;

import com.moan.pet.moanHealthPrj.domain.model.Attendance;

import java.util.List;
import java.util.Optional;

public interface IAttendanceRepository {
    List<Attendance> getAttendancesByPatientId(Long patientId);

    List<Attendance> findAll();

    Optional<Attendance> findById(Long patientId);

    Attendance create(Attendance attendance);

    Attendance getById(Long attendanceId);
}
