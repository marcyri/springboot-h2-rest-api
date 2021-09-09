package com.moan.pet.moanHealthPrj.persistance.dao;

import com.moan.pet.moanHealthPrj.persistance.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaPatientRepository extends JpaRepository<PatientEntity, Long> {
    List<PatientEntity> findByAttendances_Id(Long attendanceId);
}
