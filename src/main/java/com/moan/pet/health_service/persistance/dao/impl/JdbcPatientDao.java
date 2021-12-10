package com.moan.pet.health_service.persistance.dao.impl;

import com.moan.pet.health_service.persistance.dao.IDao;
import com.moan.pet.health_service.persistance.entity.PatientEntity;
import com.moan.pet.health_service.persistance.mapper.PatientEntityRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JdbcPatientDao implements IDao<PatientEntity, Long> {
    private final JdbcTemplate jdbcTemplate;

    public JdbcPatientDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<PatientEntity> findAll() {
        String sql = "SELECT p.* FROM patient p;";
//        String sql = "SELECT p.*, a.* FROM patient p " +
//                "LEFT JOIN patients_attendances pa on p.id=pa.patient_id " +
//                "LEFT JOIN attendance a ON pa.attendance_id=a.id";
        return jdbcTemplate.query(
                sql,
                new PatientEntityRowMapper());
    }

//    private final ResultSetExtractor<Tuple2<PatientEntity, Set<AttendanceEntity>>> resultSetExtractor =
//            JdbcTemplateMapperFactory
//                    .newInstance()
//                    .addKeys("id") // the column name you expect the user id to be on
//                    .newResultSetExtractor(new TypeReference<Tuple2<PatientEntity, List<AttendanceEntity>>>{});


    @Override
    public Optional<PatientEntity> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public List<PatientEntity> findByAttendances_Id(Long attendanceId) {
        return null;
    }

    @Override
    public PatientEntity save(PatientEntity patient) {
        return null;
    }

    @Override
    public PatientEntity getById(Long aLong) {
        return null;
    }
}
