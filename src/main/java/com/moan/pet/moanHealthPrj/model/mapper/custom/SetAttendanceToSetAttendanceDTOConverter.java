package com.moan.pet.moanHealthPrj.model.mapper.custom;

import com.moan.pet.moanHealthPrj.model.dto.AttendanceDTO;
import com.moan.pet.moanHealthPrj.model.entity.Attendance;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;

import java.util.Set;
import java.util.stream.Collectors;

public class SetAttendanceToSetAttendanceDTOConverter implements Converter<Set<Attendance>, Set<AttendanceDTO>> {
    private final ModelMapper modelMapper;

    public SetAttendanceToSetAttendanceDTOConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Set<AttendanceDTO> convert(MappingContext<Set<Attendance>, Set<AttendanceDTO>> mappingContext) {
        Set<Attendance> source = mappingContext.getSource();

        return source.stream()
                .map(s -> modelMapper.map(s, AttendanceDTO.class))
                .collect(Collectors.toSet());
    }
}
