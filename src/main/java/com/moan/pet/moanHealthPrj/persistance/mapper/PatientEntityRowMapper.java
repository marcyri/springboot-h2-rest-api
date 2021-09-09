package com.moan.pet.moanHealthPrj.persistance.mapper;

import com.moan.pet.moanHealthPrj.persistance.entity.Gender;
import com.moan.pet.moanHealthPrj.persistance.entity.PatientEntity;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PatientEntityRowMapper implements RowMapper<PatientEntity> {
    @Override
    public PatientEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        PatientEntity patient = new PatientEntity();
        patient.setId(rs.getLong("ID"));
        patient.setFamilyName(rs.getString("FAMILY_NAME"));
        patient.setName(rs.getString("NAME"));
        patient.setGender(Gender.valueOf(rs.getString("GENDER")));
        patient.setDateOfBirth(rs.getDate("DATE_OF_BIRTH"));
        patient.setCreatedDtTm(rs.getTimestamp("CREATED_DT_TM").toLocalDateTime());
        patient.setUpdatedDtTm(rs.getTimestamp("UPDATED_DT_TM").toLocalDateTime());
        return patient;
    }
}
