package com.moan.pet.moanHealthPrj.model.mapper;


import com.moan.pet.moanHealthPrj.model.dto.NestedAttendanceDTO;
import com.moan.pet.moanHealthPrj.model.dto.PatientDTO;
import com.moan.pet.moanHealthPrj.model.entity.Attendance;
import com.moan.pet.moanHealthPrj.model.entity.Patient;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PatientMapper {
    private final ModelMapper modelMapper;

    public PatientMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Patient convert(PatientDTO patientDto) {
        return modelMapper.map(patientDto, Patient.class);
    }

    public PatientDTO convert(Patient patient) {
        return modelMapper.map(patient, PatientDTO.class);
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

    public Set<NestedAttendanceDTO> convert(Set<Attendance> attendances) {
        return modelMapper.map(attendances, new TypeToken<Set<NestedAttendanceDTO>>() {}.getType());
    }
}
