package com.moan.pet.moanHealthPrj.persistance.repository;

import com.cosium.spring.data.jpa.entity.graph.domain.EntityGraphs;
import com.moan.pet.moanHealthPrj.domain.model.Attendance;
import com.moan.pet.moanHealthPrj.domain.repository.IAttendanceRepository;
import com.moan.pet.moanHealthPrj.persistance.dao.JpaAttendanceRepository;
import com.moan.pet.moanHealthPrj.persistance.entity.AttendanceEntity;
import com.moan.pet.moanHealthPrj.persistance.mapper.AttendanceMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
public class AttendanceRepository implements IAttendanceRepository {
    private JpaAttendanceRepository attendanceDAO;
    private AttendanceMapper mapper;

    public AttendanceRepository(JpaAttendanceRepository attendanceDAO, AttendanceMapper mapper) {
        this.attendanceDAO = attendanceDAO;
        this.mapper = mapper;
    }

    @Override
    public List<Attendance> findAll() {
        return mapper.convert(attendanceDAO.findAll());
    }

    @Override
    public List<Attendance> findAllWithNestedPatients() {
        List<AttendanceEntity> result =
                StreamSupport.stream(attendanceDAO.findAll(EntityGraphs.named("Attendance.patients")).spliterator(), false)
                        .collect(Collectors.toList());
        return mapper.convert(result);
    }

    @Override
    public Optional<Attendance> findById(Long attendanceId) {
        Optional<AttendanceEntity> attendanceEntity = attendanceDAO.findById(attendanceId);
        return attendanceEntity.map(entity -> mapper.convert(entity));
    }

    @Override
    public List<Attendance> getAttendancesByPatientId(Long patientId) {
        return mapper.convert(attendanceDAO.findByPatients_Id(patientId));
    }
}
