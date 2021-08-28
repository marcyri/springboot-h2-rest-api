package com.moan.pet.moanHealthPrj.persistance.mapper;

import com.moan.pet.moanHealthPrj.domain.model.Attendance;
import com.moan.pet.moanHealthPrj.persistance.entity.AttendanceEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AttendanceMapper {
    private final AttendanceMapStruct mapper;

    public AttendanceMapper(AttendanceMapStruct mapper) {
        this.mapper = mapper;
    }

    public Attendance convert(AttendanceEntity attendanceEntity) {
        return mapper.attendanceEntityToAttendance(attendanceEntity);
    }

    public List<Attendance> convert(List<AttendanceEntity> attendanceEntities) {
        return mapper.attendanceEntityToAttendance(attendanceEntities);
    }

    public Optional<Attendance> convert(Optional<AttendanceEntity> attendanceEntity) {
        return mapper.attendanceEntityToAttendance(attendanceEntity);
    }

    // TODO: TBD
    public AttendanceEntity convert(Attendance attendance) {
        return null;
    }
}
