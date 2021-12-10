package com.moan.pet.health_service.persistance.mapper;

import com.moan.pet.health_service.domain.model.Attendance;
import com.moan.pet.health_service.persistance.entity.AttendanceEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Spy;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AttendanceMapperTest {

    private AttendanceMapper sut;
    @Spy
    private final AttendanceMapStruct attendanceMapStruct = Mappers.getMapper(AttendanceMapStruct.class);
    @Spy
    private final PatientMapStruct patientMapStruct = Mappers.getMapper(PatientMapStruct.class);

    @BeforeEach
    void setUp() {
        sut = new AttendanceMapper(attendanceMapStruct);
        ReflectionTestUtils.setField(attendanceMapStruct, "patientMapStruct", patientMapStruct);
    }

    @Test
    void convert_happyPathEntityToModel() {
        // given
        AttendanceEntity entity = new AttendanceEntity();
        entity.setId(42L);

        // when
        Attendance model = sut.convert(entity);

        // then
        assertThat(model)
                .hasFieldOrPropertyWithValue("id", 42L);
    }

    @Test
    void convert_happyPathListEntitiesToListModels() {
        List<AttendanceEntity> entities = List.of(
                (AttendanceEntity) (new AttendanceEntity().setReason("entity reason 01").setDiagnosis("diagnosis 01")).setId(12L),
                (AttendanceEntity) (new AttendanceEntity().setReason("entity reason 02").setDiagnosis("diagnosis 02")).setId(13L),
                (AttendanceEntity) (new AttendanceEntity().setReason("entity reason 03").setDiagnosis("diagnosis 03")).setId(14L)
        );

        List<Attendance> models = sut.convert(entities);

        assertThat(models)
                .isNotEmpty()
                .hasSize(3)
                .extracting(Attendance::getReason)
                .anyMatch(value -> value.matches("entity reason 01")) // shorter with java.lang.String#matches
                .anySatisfy(value -> assertThat(value).matches("entity reason 02")); // nicer error message with StringAssert
        assertThat(models)
                .anySatisfy(value -> assertThat(value).isInstanceOf(Attendance.class));
    }
}