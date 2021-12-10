package com.moan.pet.health_service.persistance.dao.impl;

import com.moan.pet.health_service.persistance.dao.IDao;
import com.moan.pet.health_service.persistance.dao.JpaPatientRepository;
import com.moan.pet.health_service.persistance.entity.PatientEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaPatientDao implements IDao<PatientEntity, Long> {
    private final JpaPatientRepository jpaPatientRepository;

    public JpaPatientDao(JpaPatientRepository jpaPatientRepository) {
        this.jpaPatientRepository = jpaPatientRepository;
    }

    @Override
    public List<PatientEntity> findAll() {
        return jpaPatientRepository.findAll();
    }

    @Override
    public Optional<PatientEntity> findById(Long patientId) {
        return jpaPatientRepository.findById(patientId);
    }

    @Override
    public List<PatientEntity> findByAttendances_Id(Long attendanceId) {
        return jpaPatientRepository.findByAttendances_Id(attendanceId);
    }

    @Override
    public PatientEntity save(PatientEntity patient) {
        return jpaPatientRepository.save(patient);
    }

    @Override
    public PatientEntity getById(Long patientId) {
        return jpaPatientRepository.getById(patientId);
    }
}
