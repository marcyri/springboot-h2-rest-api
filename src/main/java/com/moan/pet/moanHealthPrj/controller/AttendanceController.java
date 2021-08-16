package com.moan.pet.moanHealthPrj.controller;

import com.moan.pet.moanHealthPrj.model.dto.AttendanceDTO;
import com.moan.pet.moanHealthPrj.model.dto.PatientDTO;
import com.moan.pet.moanHealthPrj.services.IAttendanceService;
import com.moan.pet.moanHealthPrj.services.IPatientService;
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
@RequestMapping("/api/attendance")
public class AttendanceController {
    private final IAttendanceService attendanceService;
    private final IPatientService patientService;

    AttendanceController(IAttendanceService attendanceService, IPatientService patientService) {
        this.attendanceService = attendanceService;
        this.patientService = patientService;
    }

    @GetMapping(produces = { "application/hal+json" })
    ResponseEntity<CollectionModel<AttendanceDTO>> all() {
        List<AttendanceDTO> attendances = attendanceService.getAllWithNested();
        for (AttendanceDTO attendance : attendances) {
            Long attendanceId = attendance.getId();
            Link selfLink = linkTo(AttendanceController.class).slash(attendanceId).withSelfRel();
            attendance.add(selfLink);
            if (attendance.getPatients().size() > 0) {
                Link patientsLink = linkTo(methodOn(AttendanceController.class)
                        .getPatientsForAttendance(attendanceId)).withRel("allPatients");
                attendance.add(patientsLink);
            }
        }
        Link link = linkTo(AttendanceController.class).withSelfRel();
        CollectionModel<AttendanceDTO> result = CollectionModel.of(attendances, link);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/{attendanceId}", produces = { "application/hal+json" })
    ResponseEntity<AttendanceDTO> one(@PathVariable Long attendanceId) {
        AttendanceDTO attendance = attendanceService.getOneById(attendanceId);
        Link selfLink = linkTo(AttendanceController.class).slash(attendanceId).withSelfRel();
        attendance.add(selfLink);
        Link link = linkTo(methodOn(AttendanceController.class).all()).withSelfRel();
        attendance.add(link);

        return new ResponseEntity<>(attendance, HttpStatus.OK);
    }

    @GetMapping(value = "/{attendanceId}/patients", produces = { "application/hal+json" })
    public ResponseEntity<CollectionModel<PatientDTO>> getPatientsForAttendance(@PathVariable final Long attendanceId) {
        List<PatientDTO> patients = patientService.findByAttendanceId(attendanceId);
        for (final PatientDTO patient : patients) {
            Link selfLink = linkTo(methodOn(PatientController.class).one(patient.getId())).withSelfRel();
            patient.add(selfLink);
        }
        Link link = linkTo(methodOn(AttendanceController.class).getPatientsForAttendance(attendanceId)).withSelfRel();
        CollectionModel<PatientDTO> result = CollectionModel.of(patients, link);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
