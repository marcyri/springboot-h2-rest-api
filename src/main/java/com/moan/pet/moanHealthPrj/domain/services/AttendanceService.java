package com.moan.pet.moanHealthPrj.domain.services;

import com.moan.pet.moanHealthPrj.app.dto.AttendanceDTO;
import com.moan.pet.moanHealthPrj.app.exception.NotFoundException;
import com.moan.pet.moanHealthPrj.app.exception.PatientNotFoundException;
import com.moan.pet.moanHealthPrj.domain.mapper.AttendanceMapper;
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
    public List<AttendanceDTO> getAll() {
        return attendanceMapper.convert(attendanceRepository.findAll());
    }

    @Override
    public List<AttendanceDTO> getAllWithNested() {
        return attendanceMapper.convert(attendanceRepository.findAllWithNestedPatients());
    }

    @Override
    public AttendanceDTO getOneById(Long id) {
        Attendance patient = attendanceRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException(id));

        return attendanceMapper.convert(patient);
    }

    @Override
    public List<AttendanceDTO> findByPatientId(Long patientId) {
        List<Attendance> patients = Optional.of(attendanceRepository.getAttendancesByPatientId(patientId))
                .orElseThrow(() -> new NotFoundException("Attendances for patient with id " + patientId));

        return attendanceMapper.convert(patients);
    }
}