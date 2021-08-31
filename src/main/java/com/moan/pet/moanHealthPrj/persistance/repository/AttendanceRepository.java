package com.moan.pet.moanHealthPrj.persistance.repository;

import com.moan.pet.moanHealthPrj.domain.model.Attendance;
import com.moan.pet.moanHealthPrj.domain.repository.IAttendanceRepository;
import com.moan.pet.moanHealthPrj.persistance.dao.JpaAttendanceRepository;
import com.moan.pet.moanHealthPrj.persistance.entity.AttendanceEntity;
import com.moan.pet.moanHealthPrj.persistance.mapper.AttendanceMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
    public Optional<Attendance> findById(Long attendanceId) {
        Optional<AttendanceEntity> attendanceEntity = attendanceDAO.findById(attendanceId);
        return attendanceEntity.map(entity -> mapper.convert(entity));
    }

    @Override
    public List<Attendance> getAttendancesByPatientId(Long patientId) {
        return mapper.convert(attendanceDAO.findByPatients_Id(patientId));
    }
}
