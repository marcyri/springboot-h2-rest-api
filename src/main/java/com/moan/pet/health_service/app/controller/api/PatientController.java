package com.moan.pet.health_service.app.controller.api;

import com.moan.pet.health_service.app.dto.AttendanceDTO;
import com.moan.pet.health_service.app.dto.PatientDTO;
import com.moan.pet.health_service.app.mapper.AttendanceMapper;
import com.moan.pet.health_service.app.mapper.PatientMapper;
import com.moan.pet.health_service.domain.model.Attendance;
import com.moan.pet.health_service.domain.model.Patient;
import com.moan.pet.health_service.domain.service.IAttendanceService;
import com.moan.pet.health_service.domain.service.IPatientService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {
    private final IPatientService patientService;
    private final IAttendanceService attendanceService;
    private final PatientMapper patientMapper;
    private final AttendanceMapper attendanceMapper;

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

    @PutMapping(value = "/{patientId}")
    public ResponseEntity<PatientDTO> updatePatient(@PathVariable Long patientId,
                                                    @RequestBody PatientDTO forUpdatePatientDto) {
        Patient forUpdatedPatient = patientMapper.convert(forUpdatePatientDto);
        Patient updatedPatient = patientService.update(patientId, forUpdatedPatient);
        PatientDTO updatedPatientDto = patientMapper.convert(updatedPatient);
        return new ResponseEntity<>(updatedPatientDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{patientId}/attendance")
    public ResponseEntity<PatientDTO> addNewAttendanceToPatient(@PathVariable Long patientId,
                                                                @RequestBody AttendanceDTO addAttendance) {
         Attendance attendance = attendanceService.create(attendanceMapper.convert(addAttendance));
        Patient patient = patientService.update(patientId, attendance);
        PatientDTO patientDTO = patientMapper.convert(patient);
        return new ResponseEntity<>(patientDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{patientId}/attendance/{attendanceId}")
    public ResponseEntity<PatientDTO> addExistAttendanceToPatient(@PathVariable Long patientId,
                                                                  @PathVariable Long attendanceId) {
        Patient patient = patientService.update(patientId, attendanceId);
        PatientDTO patientDTO = patientMapper.convert(patient);
        return new ResponseEntity<>(patientDTO, HttpStatus.CREATED);
    }
}
