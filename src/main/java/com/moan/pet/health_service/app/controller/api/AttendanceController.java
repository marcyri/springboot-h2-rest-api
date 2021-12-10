package com.moan.pet.health_service.app.controller.api;

import com.moan.pet.health_service.app.dto.AttendanceDTO;
import com.moan.pet.health_service.app.dto.PatientDTO;
import com.moan.pet.health_service.app.mapper.AttendanceMapper;
import com.moan.pet.health_service.app.mapper.PatientMapper;
import com.moan.pet.health_service.domain.model.Attendance;
import com.moan.pet.health_service.domain.service.IAttendanceService;
import com.moan.pet.health_service.domain.service.IPatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/attendances")
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
    public ResponseEntity<List<AttendanceDTO>> getAllAttendances() {
        List<AttendanceDTO> attendances = attendanceMapper.convert(attendanceService.getAll());
        return new ResponseEntity<>(attendances, HttpStatus.OK);
    }

    @GetMapping("/{attendanceId}")
    public ResponseEntity<AttendanceDTO> getAttendance(@PathVariable Long attendanceId) {
        AttendanceDTO attendance = attendanceMapper.convert(attendanceService.getOneById(attendanceId));
        return new ResponseEntity<>(attendance, HttpStatus.OK);
    }

    @GetMapping("/{attendanceId}/patients")
    public ResponseEntity<List<PatientDTO>> getPatientsForAttendance(@PathVariable final Long attendanceId) {
        List<PatientDTO> patients = patientMapper.convert(patientService.findByAttendanceId(attendanceId));
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<AttendanceDTO> createAttendance(@RequestBody AttendanceDTO newAttendanceDto) {
        Attendance newAttendance = attendanceMapper.convert(newAttendanceDto);
        Attendance attendance = attendanceService.create(newAttendance);
        AttendanceDTO attendanceDto = attendanceMapper.convert(attendance);
        return new ResponseEntity<>(attendanceDto, HttpStatus.CREATED);
    }
}
