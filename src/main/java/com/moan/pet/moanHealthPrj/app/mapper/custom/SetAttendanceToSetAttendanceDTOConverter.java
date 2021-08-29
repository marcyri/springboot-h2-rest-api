package com.moan.pet.moanHealthPrj.app.mapper.custom;

import com.moan.pet.moanHealthPrj.app.dto.AttendanceDTO;
import com.moan.pet.moanHealthPrj.persistance.entity.AttendanceEntity;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;

import java.util.Set;
import java.util.stream.Collectors;

public class SetAttendanceToSetAttendanceDTOConverter implements Converter<Set<AttendanceEntity>, Set<AttendanceDTO>> {
    private final ModelMapper modelMapper;

    public SetAttendanceToSetAttendanceDTOConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Set<AttendanceDTO> convert(MappingContext<Set<AttendanceEntity>, Set<AttendanceDTO>> mappingContext) {
        Set<AttendanceEntity> source = mappingContext.getSource();

        return source.stream()
                .map(s -> modelMapper.map(s, AttendanceDTO.class))
                .collect(Collectors.toSet());
    }
}
