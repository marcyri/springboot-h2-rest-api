package com.moan.pet.health_service.persistance.dao;

import com.moan.pet.health_service.persistance.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaPatientRepository extends JpaRepository<PatientEntity, Long> {
    List<PatientEntity> findByAttendances_Id(Long attendanceId);
}
