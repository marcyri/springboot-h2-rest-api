package com.moan.pet.moanHealthPrj.persistance.dao;

import com.moan.pet.moanHealthPrj.persistance.entity.AttendanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaAttendanceRepository extends JpaRepository<AttendanceEntity, Long> {
    List<AttendanceEntity> findByPatients_Id(Long id);
}
