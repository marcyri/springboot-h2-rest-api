package com.moan.pet.health_service.app.mapper.custom;

import com.moan.pet.health_service.app.dto.PatientDTO;
import com.moan.pet.health_service.persistance.entity.PatientEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

public class PatientToPatientDTOMap extends PropertyMap<PatientEntity, PatientDTO> {
    private final ModelMapper modelMapper;

    public PatientToPatientDTOMap(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    protected void configure() {
//        map().setPropertyOfOther(source.getPropertyOfOther());
//        map().add(source.)
//        map(source, destination);
        using(new SetAttendanceToSetAttendanceDTOConverter(modelMapper)).map(source.getAttendances()).setAttendances(null);
//        source.stream()
//            .forEach(el -> using(new SetAttendanceToSetAttendanceDTOConverter(modelMapper)));
//        source.stream()
//                .map(el -> using(new SetAttendanceToSetAttendanceDTOConverter(modelMapper)).map(source));
//        using(new SetAttendanceToSetAttendanceDTOConverter(modelMapper)).map(source);
    }
}