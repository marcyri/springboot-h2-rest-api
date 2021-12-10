package com.moan.pet.health_service.persistance.repository;

import com.moan.pet.health_service.persistance.dao.JpaAttendanceRepository;
import com.moan.pet.health_service.persistance.entity.AttendanceEntity;
import com.moan.pet.health_service.persistance.entity.BaseEntity;
import com.moan.pet.health_service.persistance.mapper.AttendanceMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DataJpaTest
@DisplayName("Attendance Repository Interaction Based (London school) Tests")
class AttendanceRepositoryInteractionBasedTest {

	@InjectMocks
	private AttendanceRepository subject;

	@Mock
	private JpaAttendanceRepository attendanceDao;

	@Mock
	private AttendanceMapper attendanceMapper;

	@Captor
	private ArgumentCaptor<Long> longArgumentCaptor;

	@Captor
	private ArgumentCaptor<Optional<AttendanceEntity>> optionalArgumentCaptor;

	@Test
	void findAll() {

	}

	@Test
	void findById_givenId_findByIdWithGivenIdWasCalled() {
		// given
		Long expectedAttendanceId = 10L;

		// when
		subject.findById(expectedAttendanceId);

		// then
		verify(attendanceDao).findById(longArgumentCaptor.capture());
		Long actualAttendanceId = longArgumentCaptor.getValue();

		assertThat(actualAttendanceId).isEqualTo(expectedAttendanceId);
	}

	@Test
	void findById_givenDaoFindByIdResponse_mapperConvertWithDaoFindByIdResponseWasCalled() {
		// given
		AttendanceEntity attendanceEntity = new AttendanceEntity();
		attendanceEntity.setId(10L);
		attendanceEntity.setReason("Expected reason");
		Optional<AttendanceEntity> attendanceEntityOptional = Optional.of(attendanceEntity);
		when(attendanceDao.findById(any())).thenReturn(attendanceEntityOptional);

		// when
		subject.findById(any());

		// then
		verify(attendanceMapper).convert(optionalArgumentCaptor.capture());
		Optional<AttendanceEntity> actualAttendanceEntityOptional = optionalArgumentCaptor.getValue();

		assertThat(actualAttendanceEntityOptional).isNotEmpty();
		assertThat(actualAttendanceEntityOptional)
				.isPresent()
				.map(BaseEntity::getId)
				.get()
				.isEqualTo(attendanceEntity.getId());
		assertThat(actualAttendanceEntityOptional)
				.isPresent()
				.map(AttendanceEntity::getReason)
				.get()
				.isEqualTo(attendanceEntity.getReason());
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