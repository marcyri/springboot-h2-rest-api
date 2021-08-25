package com.moan.pet.moanHealthPrj.persistance.dao;

import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaRepository;
import com.moan.pet.moanHealthPrj.persistance.entity.AttendanceEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaAttendanceRepository extends EntityGraphJpaRepository<AttendanceEntity, Long> {
    List<AttendanceEntity> findByPatients_Id(Long id);
}
