package com.moan.pet.moanHealthPrj.persistance.repository;

import com.moan.pet.moanHealthPrj.domain.model.Attendance;
import com.moan.pet.moanHealthPrj.domain.repository.IAttendanceRepository;
import com.moan.pet.moanHealthPrj.persistance.dao.JpaAttendanceRepository;
import com.moan.pet.moanHealthPrj.persistance.mapper.AttendanceMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Repository
public class AttendanceRepository implements IAttendanceRepository {
    private final JpaAttendanceRepository attendanceDao;
    private final AttendanceMapper attendanceMapper;

    @Override
    public List<Attendance> findAll() {
        return attendanceMapper.convert(attendanceDao.findAll());
    }

    @Override
    public Optional<Attendance> findById(Long attendanceId) {
        return attendanceMapper.convert(attendanceDao.findById(attendanceId));
    }

    @Override
    public List<Attendance> getAttendancesByPatientId(Long patientId) {
        return attendanceMapper.convert(attendanceDao.findByPatients_Id(patientId));
    }

    @Override
    public Attendance create(Attendance attendance) {
        return attendanceMapper.convert(attendanceDao.save(attendanceMapper.convert(attendance)));
    }

    @Override
    public Attendance getById(Long attendanceId) {
        return attendanceMapper.convert(attendanceDao.getById(attendanceId));
    }
}
