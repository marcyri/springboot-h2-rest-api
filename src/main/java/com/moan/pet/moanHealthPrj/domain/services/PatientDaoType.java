package com.moan.pet.moanHealthPrj.domain.services;

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
