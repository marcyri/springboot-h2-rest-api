package com.moan.pet.moanHealthPrj.model.mapper;


import com.moan.pet.moanHealthPrj.model.dto.AttendanceDTO;
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
public class AttendanceMapper {
    private final ModelMapper modelMapper;

    public AttendanceMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Attendance convert(AttendanceDTO attendanceDTO) {
        return modelMapper.map(attendanceDTO, Attendance.class);
    }

    public AttendanceDTO convert(Attendance attendance) {
        return modelMapper.map(attendance, AttendanceDTO.class);
    }

    public List<AttendanceDTO> convert(List<Attendance> attendances) {
        return attendances.stream()
                .map(attendance -> {
                    AttendanceDTO curAttendanceDTO = convert(attendance);
                    curAttendanceDTO.setPatients(convert(attendance.getPatients()));
                    return curAttendanceDTO;
                })
                .collect(Collectors.toList());
    }

    public Set<PatientDTO> convert(Set<Patient> patients) {
        return modelMapper.map(patients, new TypeToken<Set<PatientDTO>>() {}.getType());
    }
}
