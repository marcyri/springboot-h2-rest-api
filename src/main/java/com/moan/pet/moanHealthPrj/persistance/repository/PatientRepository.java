package com.moan.pet.moanHealthPrj.persistance.repository;

import com.moan.pet.moanHealthPrj.domain.model.Attendance;
import com.moan.pet.moanHealthPrj.domain.model.Patient;
import com.moan.pet.moanHealthPrj.domain.repository.IPatientRepository;
import com.moan.pet.moanHealthPrj.persistance.dao.IDao;
import com.moan.pet.moanHealthPrj.persistance.dao.helper.DaoTypeHolder;
import com.moan.pet.moanHealthPrj.persistance.dao.helper.PatientDaoType;
import com.moan.pet.moanHealthPrj.persistance.entity.AttendanceEntity;
import com.moan.pet.moanHealthPrj.persistance.entity.PatientEntity;
import com.moan.pet.moanHealthPrj.persistance.mapper.AttendanceMapper;
import com.moan.pet.moanHealthPrj.persistance.mapper.PatientMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Repository
public class PatientRepository implements IPatientRepository {
    private final Map<String, IDao<PatientEntity, Long>> patientDAOs;
    private final PatientMapper patientMapper;
    private final AttendanceMapper attendanceMapper;
//    private final JpaAttendanceRepository attendanceDao;
    private final AttendanceRepository attendanceRepository;

    private IDao<PatientEntity, Long> getPatientDao() {
        return patientDAOs.get(PatientDaoType.valueOf(DaoTypeHolder.getDaoType().toString()).getValue());
    }

    @Override
    public List<Patient> findAll() {
        return patientMapper.convert(getPatientDao().findAll());
    }

    @Override
    public Optional<Patient> findById(Long patientId) {
        return patientMapper.convert(getPatientDao().findById(patientId));
    }

    @Override
    public List<Patient> getPatientsByAttendanceId(Long attendanceId) {
        return patientMapper.convert(getPatientDao().findByAttendances_Id(attendanceId));
    }

    @Override
    public Patient create(Patient patient) {
        return patientMapper.convert(getPatientDao().save(patientMapper.convert(patient)));
    }

    @Override
    public Patient update(Long id, Patient patient) {
        PatientEntity updatedPatient = getPatientDao().getById(id);
        PatientEntity forUpdatePatient = patientMapper.convert(patient);

        if (!ObjectUtils.isEmpty(forUpdatePatient.getName())) {
            updatedPatient.setFamilyName(forUpdatePatient.getFamilyName());
        }
        if (!ObjectUtils.isEmpty(forUpdatePatient.getName())) {
            updatedPatient.setName(forUpdatePatient.getName());
        }
        if (!ObjectUtils.isEmpty(forUpdatePatient.getGender())) {
            updatedPatient.setGender(forUpdatePatient.getGender());
        }
        if (!ObjectUtils.isEmpty(forUpdatePatient.getDateOfBirth())) {
            updatedPatient.setDateOfBirth(forUpdatePatient.getDateOfBirth());
        }
        if (!CollectionUtils.isEmpty(forUpdatePatient.getAttendances())) {
            updatedPatient.setAttendances(forUpdatePatient.getAttendances());
        }
        
        return patientMapper.convert(getPatientDao().save(updatedPatient));
    }

    @Override
    public Patient update(Long patientId, Attendance attendance) {
        PatientEntity patientEntity = getPatientDao().getById(patientId);
        AttendanceEntity attendanceEntity = attendanceMapper.convert(attendance);
        patientEntity.getAttendances().add(attendanceEntity);
        PatientEntity updatedPatientEntity = getPatientDao().save(patientEntity);
        return patientMapper.convert(updatedPatientEntity);
    }
}
