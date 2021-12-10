package com.moan.pet.health_service.persistance.dao.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Sql({ "/test_schema.sql", "/test_data.sql" })
@Slf4j
class JdbcPatientDaoTest {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private JdbcPatientDao subject;

	@BeforeEach
	void setUp() {
		subject = new JdbcPatientDao(jdbcTemplate);
	}

	@Test
	void findAll() {
		var patients = subject.findAll();
		assertThat(patients.size()).isEqualTo(7);
	}

	@Test
	void findById() {
	}

	@Test
	void findByAttendances_Id() {
	}

	@Test
	void save() {
	}

	@Test
	void getById() {
	}
}