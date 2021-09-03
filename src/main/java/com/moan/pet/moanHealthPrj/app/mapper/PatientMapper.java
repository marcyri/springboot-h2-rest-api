package com.moan.pet.moanHealthPrj.app.mapper;


import com.moan.pet.moanHealthPrj.app.dto.AttendanceDTO;
import com.moan.pet.moanHealthPrj.app.dto.PatientDTO;
import com.moan.pet.moanHealthPrj.domain.model.Attendance;
import com.moan.pet.moanHealthPrj.domain.model.Patient;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component("AppPatientMapper")
public class PatientMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public AttendanceMapper attendanceMapper;

    public PatientMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Patient convert(PatientDTO patientDto) {
        return modelMapper.map(patientDto, Patient.class);
    }

    public PatientDTO convert(Patient patient) {
        PatientDTO curPatient = modelMapper.map(patient, PatientDTO.class);
        curPatient.setAttendances(convert(patient.getAttendances()));
        return curPatient;
    }

    public List<PatientDTO> convert(List<Patient> patients) {
        return patients.stream()
                .map(patient -> {
                    PatientDTO curPatientDTO = convert(patient);

                    curPatientDTO.setAttendances(convert(patient.getAttendances()));
                    return curPatientDTO;
                })
                .collect(Collectors.toList());
    }

    public Set<AttendanceDTO> convert(Set<Attendance> attendances) {
        if (ObjectUtils.isEmpty(attendances)) {
            return null;
        }
        Set<AttendanceDTO> result = attendances.stream()
                .map(attendance -> attendanceMapper.convert(attendance))
                .peek(System.out::println)
                .collect(Collectors.toSet());
        return result;
    }
}
