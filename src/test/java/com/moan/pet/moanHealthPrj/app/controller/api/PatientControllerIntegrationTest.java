package com.moan.pet.moanHealthPrj.app.controller.api;

import com.moan.pet.moanHealthPrj.domain.model.Patient;
import com.moan.pet.moanHealthPrj.domain.service.IAttendanceService;
import com.moan.pet.moanHealthPrj.domain.service.IPatientService;
import com.moan.pet.moanHealthPrj.persistance.dao.JpaAttendanceRepository;
import com.moan.pet.moanHealthPrj.persistance.dao.impl.JdbcPatientDao;
import com.moan.pet.moanHealthPrj.persistance.dao.impl.JpaPatientDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@WebMvcTest(PatientController.class)
class PatientControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;


    @BeforeEach
    void setUp() {
    }

    @MockBean
    private IPatientService patientService;
    @MockBean
    private IAttendanceService attendanceService;
    @MockBean
    private JdbcPatientDao jdbcPatientDao;
    @MockBean
    private JpaPatientDao jpaPatientDao;
    @MockBean
    private JpaAttendanceRepository jpaAttendanceRepository;

    private RequestBuilder requestBuilder;

    @Test
    @Disabled
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
}