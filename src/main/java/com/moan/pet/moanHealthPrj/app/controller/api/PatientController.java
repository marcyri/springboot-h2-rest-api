package com.moan.pet.moanHealthPrj.app.controller.api;

import com.moan.pet.moanHealthPrj.app.dto.AttendanceDTO;
import com.moan.pet.moanHealthPrj.app.dto.PatientDTO;
import com.moan.pet.moanHealthPrj.app.mapper.AttendanceMapper;
import com.moan.pet.moanHealthPrj.app.mapper.PatientMapper;
import com.moan.pet.moanHealthPrj.domain.services.IAttendanceService;
import com.moan.pet.moanHealthPrj.domain.services.IPatientService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/patient")
public class PatientController {
    private final IPatientService patientService;
    private final IAttendanceService attendanceService;
    private final PatientMapper patientMapper;
    private final AttendanceMapper attendanceMapper;

    PatientController(IPatientService patientService, IAttendanceService attendanceService,
                      PatientMapper patientMapper, AttendanceMapper attendanceMapper) {
        this.patientService = patientService;
        this.attendanceService = attendanceService;
        this.patientMapper = patientMapper;
        this.attendanceMapper = attendanceMapper;
    }

    @GetMapping(produces = { "application/hal+json" })
    ResponseEntity<CollectionModel<PatientDTO>> all() {
        List<PatientDTO> patients = patientMapper.convert(patientService.getAll());
        for (PatientDTO patient : patients) {
            Long patientId = patient.getId();
            Link selfLink = linkTo(PatientController.class).slash(patientId).withSelfRel();
            patient.add(selfLink);
            if (patient.getAttendances().size() > 0) {
                Link attendancesLink = linkTo(methodOn(PatientController.class)
                        .getAttendancesForPatient(patientId)).withRel("allAttendances");
                patient.add(attendancesLink);
            }
        }
        Link link = linkTo(PatientController.class).withSelfRel();
        CollectionModel<PatientDTO> result = CollectionModel.of(patients, link);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/{patientId}", produces = { "application/hal+json" })
    ResponseEntity<PatientDTO> one(@PathVariable Long patientId) {
        PatientDTO patient = patientMapper.convert(patientService.getOneById(patientId));
        Link selfLink = linkTo(PatientController.class).slash(patientId).withSelfRel();
        patient.add(selfLink);
        Link link = linkTo(methodOn(PatientController.class).all()).withRel("allPatients");
        patient.add(link);
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    @GetMapping(value = "/{patientId}/attendances", produces = { "application/hal+json" })
    public ResponseEntity<CollectionModel<AttendanceDTO>> getAttendancesForPatient(@PathVariable final Long patientId) {
        List<AttendanceDTO> attendances = attendanceMapper.convert(attendanceService.findByPatientId(patientId));
        for (final AttendanceDTO attendance : attendances) {
            Link selfLink = linkTo(methodOn(AttendanceController.class)
                    .one(attendance.getId())).withSelfRel();
            attendance.add(selfLink);
        }

        Link link = linkTo(methodOn(PatientController.class)
                .getAttendancesForPatient(patientId)).withSelfRel();
        CollectionModel<AttendanceDTO> result = CollectionModel.of(attendances, link);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
