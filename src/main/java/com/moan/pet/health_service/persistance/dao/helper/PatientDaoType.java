package com.moan.pet.health_service.persistance.dao.helper;

public enum PatientDaoType {
    JPA("jpaPatientDao"),
    JDBC("jdbcPatientDao");

    private final String value;

    PatientDaoType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
