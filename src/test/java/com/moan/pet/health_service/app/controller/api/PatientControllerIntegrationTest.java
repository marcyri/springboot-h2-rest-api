package com.moan.pet.health_service.app.controller.api;

import com.moan.pet.health_service.app.mapper.AttendanceMapper;
import com.moan.pet.health_service.app.mapper.PatientMapper;
import com.moan.pet.health_service.domain.model.Patient;
import com.moan.pet.health_service.domain.service.IAttendanceService;
import com.moan.pet.health_service.domain.service.IPatientService;
import com.moan.pet.health_service.persistance.dao.JpaAttendanceRepository;
import com.moan.pet.health_service.persistance.dao.impl.JdbcPatientDao;
import com.moan.pet.health_service.persistance.dao.impl.JpaPatientDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@WebMvcTest(PatientController.class)
@AutoConfigureMockMvc(addFilters = false)
class PatientControllerIntegrationTest {

	private MockMvc mockMvc;

	@MockBean
	private IPatientService patientService;
	@MockBean
	private IAttendanceService attendanceService;
//	@MockBean
//	private PatientMapper patientMapper;
//	@Autowired
//	private AttendanceMapper attendanceMapper;
//	@MockBean
//	private ModelMapper modelMapper;
	@MockBean
	private JdbcPatientDao jdbcPatientDao;
	@MockBean
	private JpaPatientDao jpaPatientDao;
	@MockBean
	private JpaAttendanceRepository jpaAttendanceRepository;

	private RequestBuilder requestBuilder;

	@Autowired
	private WebApplicationContext wac;

	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac)
				.build();
	}

	@Test
	void getPatient() throws Exception {
		requestBuilder = MockMvcRequestBuilders.get("/api/v1/patients")
				.contentType(MediaType.APPLICATION_JSON);
		when(patientService.getAll())
				.thenReturn(List.of(
						(new Patient()).setName("test01").setFamilyName("test01"),
						(new Patient()).setName("test02").setFamilyName("test02"))
				);

		ResultActions resultActions = mockMvc.perform(requestBuilder);

		resultActions.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
		assertTrue(resultActions.andReturn()
				.getResponse()
				.getContentAsString()
				.contains("test01"));
	}

	@TestConfiguration
	static class TestConfig {
		@Bean
		public ModelMapper modelMapper() {
			return new ModelMapper();
		}
		@Bean
		public PatientMapper patientMapper() {
			return new PatientMapper(modelMapper());
		}
		@Bean
		public AttendanceMapper attendanceMapper() {
			return new AttendanceMapper(modelMapper());
		}
	}
}