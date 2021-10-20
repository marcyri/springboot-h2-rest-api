package com.moan.pet.moanHealthPrj.domain.service.impl;

import com.moan.pet.moanHealthPrj.app.exception.NotFoundException;
import com.moan.pet.moanHealthPrj.app.exception.PatientNotFoundException;
import com.moan.pet.moanHealthPrj.domain.model.Attendance;
import com.moan.pet.moanHealthPrj.domain.repository.IAttendanceRepository;
import com.moan.pet.moanHealthPrj.domain.service.IAttendanceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttendanceService implements IAttendanceService {
    private final IAttendanceRepository attendanceRepository;

    public AttendanceService(IAttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
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

    @Override
    public Attendance create(Attendance attendance) {
        return attendanceRepository.create(attendance);
    }
}