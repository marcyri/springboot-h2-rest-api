package com.moan.pet.health_service.persistance.dao;

import com.moan.pet.health_service.persistance.entity.AttendanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaAttendanceRepository extends JpaRepository<AttendanceEntity, Long> {
    List<AttendanceEntity> findByPatients_Id(Long id);
}
