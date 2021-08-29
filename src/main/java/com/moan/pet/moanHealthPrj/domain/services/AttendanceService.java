package com.moan.pet.moanHealthPrj.domain.services;

import com.moan.pet.moanHealthPrj.app.exception.NotFoundException;
import com.moan.pet.moanHealthPrj.app.exception.PatientNotFoundException;
import com.moan.pet.moanHealthPrj.app.mapper.AttendanceMapper;
import com.moan.pet.moanHealthPrj.domain.model.Attendance;
import com.moan.pet.moanHealthPrj.domain.repository.IAttendanceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttendanceService implements IAttendanceService {
    IAttendanceRepository attendanceRepository;
    AttendanceMapper attendanceMapper;

    public AttendanceService(IAttendanceRepository attendanceRepository, AttendanceMapper attendanceMapper) {
        this.attendanceRepository = attendanceRepository;
        this.attendanceMapper = attendanceMapper;
    }

    @Override
    public List<Attendance> getAll() {
        return attendanceRepository.findAll();
    }

    @Override
    public Attendance getOneById(Long id) {
        return attendanceRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException(id));
    }

    @Override
    public List<Attendance> findByPatientId(Long patientId) {
        return Optional.of(attendanceRepository.getAttendancesByPatientId(patientId))
                .orElseThrow(() -> new NotFoundException("Attendances for patient with id " + patientId));
    }
}