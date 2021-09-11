package com.moan.pet.moanHealthPrj.app.controller.api;

import com.moan.pet.moanHealthPrj.app.dto.AttendanceDTO;
import com.moan.pet.moanHealthPrj.app.dto.PatientDTO;
import com.moan.pet.moanHealthPrj.app.mapper.AttendanceMapper;
import com.moan.pet.moanHealthPrj.app.mapper.PatientMapper;
import com.moan.pet.moanHealthPrj.domain.model.Patient;
import com.moan.pet.moanHealthPrj.domain.service.IAttendanceService;
import com.moan.pet.moanHealthPrj.domain.service.IPatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
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

    @GetMapping
    private ResponseEntity<List<PatientDTO>> getAllPatients() {
        List<PatientDTO> patients = patientMapper.convert(patientService.getAll());
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @GetMapping(value = "/{patientId}")
    ResponseEntity<PatientDTO> getPatient(@PathVariable Long patientId) {
        PatientDTO patient = patientMapper.convert(patientService.getOneById(patientId));
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    @GetMapping(value = "/{patientId}/attendances")
    public ResponseEntity<List<AttendanceDTO>> getAttendancesForPatient(@PathVariable final Long patientId) {
        List<AttendanceDTO> attendances = attendanceMapper.convert(attendanceService.findByPatientId(patientId));
        return new ResponseEntity<>(attendances, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<PatientDTO> createPatient(@RequestBody PatientDTO newPatientDto) {
        Patient newPatient = patientMapper.convert(newPatientDto);
        Patient patient = patientService.create(newPatient);
        PatientDTO patientDTO = patientMapper.convert(patient);
        return new ResponseEntity<>(patientDTO, HttpStatus.CREATED);
    }
}
