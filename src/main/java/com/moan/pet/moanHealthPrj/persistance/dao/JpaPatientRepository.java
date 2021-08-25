package com.moan.pet.moanHealthPrj.persistance.dao;

import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaRepository;
import com.moan.pet.moanHealthPrj.persistance.entity.PatientEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaPatientRepository extends EntityGraphJpaRepository<PatientEntity, Long> {
    List<PatientEntity> findByAttendances_Id(Long attendanceId);
}
