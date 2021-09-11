package com.moan.pet.moanHealthPrj.app.controller.api;

import com.moan.pet.moanHealthPrj.app.dto.AttendanceDTO;
import com.moan.pet.moanHealthPrj.app.dto.PatientDTO;
import com.moan.pet.moanHealthPrj.app.mapper.AttendanceMapper;
import com.moan.pet.moanHealthPrj.app.mapper.PatientMapper;
import com.moan.pet.moanHealthPrj.domain.service.IAttendanceService;
import com.moan.pet.moanHealthPrj.domain.service.IPatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/attendances")
public class AttendanceController {
    private final IAttendanceService attendanceService;
    private final IPatientService patientService;
    private final AttendanceMapper attendanceMapper;
    private final PatientMapper patientMapper;

    AttendanceController(IAttendanceService attendanceService, IPatientService patientService,
                         AttendanceMapper attendanceMapper, PatientMapper patientMapper) {
        this.attendanceService = attendanceService;
        this.patientService = patientService;
        this.attendanceMapper = attendanceMapper;
        this.patientMapper = patientMapper;
    }

    @GetMapping
    ResponseEntity<List<AttendanceDTO>> getAllAttendances() {
        List<AttendanceDTO> attendances = attendanceMapper.convert(attendanceService.getAll());
        return new ResponseEntity<>(attendances, HttpStatus.OK);
    }

    @GetMapping("/{attendanceId}")
    ResponseEntity<AttendanceDTO> getAttendance(@PathVariable Long attendanceId) {
        AttendanceDTO attendance = attendanceMapper.convert(attendanceService.getOneById(attendanceId));
        return new ResponseEntity<>(attendance, HttpStatus.OK);
    }

    @GetMapping("/{attendanceId}/patients")
    public ResponseEntity<List<PatientDTO>> getPatientsForAttendance(@PathVariable final Long attendanceId) {
        List<PatientDTO> patients = patientMapper.convert(patientService.findByAttendanceId(attendanceId));
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }
}
