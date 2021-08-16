package com.moan.pet.moanHealthPrj.services;

import com.cosium.spring.data.jpa.entity.graph.domain.EntityGraphs;
import com.moan.pet.moanHealthPrj.controller.exception.NotFoundException;
import com.moan.pet.moanHealthPrj.controller.exception.PatientNotFoundException;
import com.moan.pet.moanHealthPrj.model.dto.AttendanceDTO;
import com.moan.pet.moanHealthPrj.model.entity.Attendance;
import com.moan.pet.moanHealthPrj.model.mapper.AttendanceMapper;
import com.moan.pet.moanHealthPrj.repositories.AttendanceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AttendanceService implements IAttendanceService {
    AttendanceRepository repository;
    AttendanceMapper mapper;

    public AttendanceService(AttendanceRepository repository, AttendanceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<AttendanceDTO> getAll() {
        return mapper.convert(repository.findAll());
    }

    @Override
    public List<AttendanceDTO> getAllWithNested() {
        List<Attendance> result =
                StreamSupport.stream(repository.findAll(EntityGraphs.named("Attendance.patients")).spliterator(), false)
                        .collect(Collectors.toList());
        return mapper.convert(result);
    }

    @Override
    public AttendanceDTO getOneById(Long id) {
        Attendance patient = repository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException(id));

        return mapper.convert(patient);
    }

    @Override
    public List<AttendanceDTO> findByPatientId(Long patientId) {
        List<Attendance> patients = Optional.of(repository.findByPatients_Id(patientId))
                .orElseThrow(() -> new NotFoundException("Attendances for patient with id " + patientId));

        return mapper.convert(patients);
    }
}