package com.moan.pet.moanHealthPrj.app.controller.api;

import com.moan.pet.moanHealthPrj.app.dto.PatientDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PatientControllerRealIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private Integer port;

    @BeforeEach
    public void setUp() {
    }

    @Test
    @Disabled
    void getPatient() throws Exception {
        String queryUrl = UriComponentsBuilder.fromUriString("http://localhost:" + port)
                .path("/api/v1/patients").buildAndExpand("").toUriString().trim();
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        ResponseEntity<List<PatientDTO>> entity =
                testRestTemplate.exchange(
                        queryUrl,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<>() {}
                );
        then(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(entity.getHeaders().getContentType()).isEqualTo(MediaType.APPLICATION_JSON);
    }
}