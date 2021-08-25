package com.moan.pet.moanHealthPrj.domain.repository;

import com.cosium.spring.data.jpa.entity.graph.domain.EntityGraph;
import com.moan.pet.moanHealthPrj.domain.model.Attendance;

import java.util.List;
import java.util.Optional;

public interface IAttendanceRepository {
    List<Attendance> getAttendancesByPatientId(Long patientId);

    List<Attendance> findAll();

    List<Attendance> findAllWithNestedPatients();

    Optional<Attendance> findById(Long patientId);
}
