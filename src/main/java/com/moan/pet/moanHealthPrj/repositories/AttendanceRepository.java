package com.moan.pet.moanHealthPrj.repositories;

import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaRepository;
import com.moan.pet.moanHealthPrj.model.entity.Attendance;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceRepository extends EntityGraphJpaRepository<Attendance, Long> {
    List<Attendance> findByPatients_Id(Long id);
}
