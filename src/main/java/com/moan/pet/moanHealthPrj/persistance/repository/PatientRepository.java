package com.moan.pet.moanHealthPrj.persistance.repository;

import com.cosium.spring.data.jpa.entity.graph.domain.EntityGraphs;
import com.moan.pet.moanHealthPrj.domain.model.Patient;
import com.moan.pet.moanHealthPrj.domain.repository.IPatientRepository;
import com.moan.pet.moanHealthPrj.persistance.dao.JpaPatientRepository;
import com.moan.pet.moanHealthPrj.persistance.entity.PatientEntity;
import com.moan.pet.moanHealthPrj.persistance.mapper.PatientMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
public class PatientRepository implements IPatientRepository {
    private JpaPatientRepository patientDAO;
    private PatientMapper mapper;

    public PatientRepository(JpaPatientRepository patientDAO, PatientMapper mapper) {
        this.patientDAO = patientDAO;
        this.mapper = mapper;
    }

    @Override
    public List<Patient> findAll() {
        return mapper.convert(patientDAO.findAll());
    }

    @Override
    public List<Patient> findAllWithNestedAttendances() {
        List<PatientEntity> result =
                StreamSupport.stream(patientDAO.findAll(EntityGraphs.named("Patient.attendances")).spliterator(), false)
                        .collect(Collectors.toList());
        return mapper.convert(result);
    }
    @Override
    public Optional<Patient> findById(Long patientId) {
        return mapper.convert(patientDAO.findById(patientId));
    }

    @Override
    public List<Patient> getPatientsByAttendanceId(Long attendanceId) {
        return mapper.convert(patientDAO.findByAttendances_Id(attendanceId));
    }
}
