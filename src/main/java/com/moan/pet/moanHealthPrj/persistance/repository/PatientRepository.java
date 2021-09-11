package com.moan.pet.moanHealthPrj.persistance.repository;

import com.moan.pet.moanHealthPrj.domain.model.Patient;
import com.moan.pet.moanHealthPrj.domain.repository.IPatientRepository;
import com.moan.pet.moanHealthPrj.persistance.dao.helper.DaoTypeHolder;
import com.moan.pet.moanHealthPrj.persistance.dao.helper.PatientDaoType;
import com.moan.pet.moanHealthPrj.persistance.dao.IDao;
import com.moan.pet.moanHealthPrj.persistance.entity.PatientEntity;
import com.moan.pet.moanHealthPrj.persistance.mapper.PatientMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class PatientRepository implements IPatientRepository {
    private final Map<String, IDao<PatientEntity, Long>> patientDAOs;
    private final PatientMapper mapper;

    public PatientRepository(Map<String, IDao<PatientEntity, Long>> patientDAOs, PatientMapper mapper) {
        this.patientDAOs = patientDAOs;
        this.mapper = mapper;
    }

    private IDao<PatientEntity, Long> getDao() {
        return patientDAOs.get(PatientDaoType.valueOf(DaoTypeHolder.getDaoType().toString()).getValue());
    }

    @Override
    public List<Patient> findAll() {
        return mapper.convert(getDao().findAll());
    }

    @Override
    public Optional<Patient> findById(Long patientId) {
        return mapper.convert(getDao().findById(patientId));
    }

    @Override
    public List<Patient> getPatientsByAttendanceId(Long attendanceId) {
        return mapper.convert(getDao().findByAttendances_Id(attendanceId));
    }

    @Override
    public Patient create(Patient patient) {
        return mapper.convert(getDao().save(mapper.convert(patient)));
    }
}
