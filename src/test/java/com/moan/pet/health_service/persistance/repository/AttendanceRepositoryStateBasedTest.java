package com.moan.pet.health_service.persistance.repository;

import com.moan.pet.health_service.domain.model.Attendance;
import com.moan.pet.health_service.persistance.dao.JpaAttendanceRepository;
import com.moan.pet.health_service.persistance.mapper.AttendanceMapStruct;
import com.moan.pet.health_service.persistance.mapper.AttendanceMapper;
import com.moan.pet.health_service.persistance.mapper.PatientMapStruct;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Sql({ "/test_schema.sql", "/test_data.sql" })
@DisplayName("Attendance Repository State Based (Detroit school) Tests")
class AttendanceRepositoryStateBasedTest {

	private AttendanceRepository subject;

	@Autowired
	private JpaAttendanceRepository attendanceDao;

	private AttendanceMapStruct attendanceMapStruct;

	@BeforeEach
	void setUp() {
		PatientMapStruct patientMapStruct = Mappers.getMapper(PatientMapStruct.class);
		attendanceMapStruct = Mappers.getMapper(AttendanceMapStruct.class); // Initialization of the mapper
		ReflectionTestUtils.setField(attendanceMapStruct, "patientMapStruct", patientMapStruct);
		ReflectionTestUtils.setField(patientMapStruct, "attendanceMapStruct", attendanceMapStruct);
		AttendanceMapper attendanceMapper = new AttendanceMapper(attendanceMapStruct);
		subject = new AttendanceRepository(attendanceDao, attendanceMapper);
	}

	@Test
	void findAll() {

	}

	@Test
	void findById_givenId_findExpectedReasonAndDiagnosis() {
		// given
		Long expectedAttendanceId = 2L;
		String expectedReason = "Dental service";
		String expectedDiagnosis = "Caries";

		// when
		Optional<Attendance> actualAttendanceOptional = subject.findById(expectedAttendanceId);

		// then
		assertThat(actualAttendanceOptional).isNotEmpty();
		assertThat(actualAttendanceOptional)
				.isPresent()
				.map(Attendance::getReason)
				.get()
				.isEqualTo(expectedReason);
		assertThat(actualAttendanceOptional)
				.isPresent()
				.map(Attendance::getDiagnosis)
				.get()
				.isEqualTo(expectedDiagnosis);
	}


	@Test
	void findById_givenNotExistsId_findExpectedReasonAndDiagnosis() {
		// given
		Long expectedAttendanceId = 0L;

		// when
		Optional<Attendance> actualAttendanceOptional = subject.findById(expectedAttendanceId);

		// then
		assertThat(actualAttendanceOptional).isEmpty();
	}

	@Test
	void getAttendancesByPatientId() {
	}

	@Test
	void create() {
	}

	@Test
	void getById() {
	}
}