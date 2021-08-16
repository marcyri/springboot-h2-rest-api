package com.moan.pet.moanHealthPrj.repositories;

import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaRepository;
import com.moan.pet.moanHealthPrj.model.entity.Patient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends EntityGraphJpaRepository<Patient, Long> {
    List<Patient> findByAttendances_Id(Long attendanceId);
}
