package com.moan.pet.moanHealthPrj.persistance.mapper;

import com.moan.pet.moanHealthPrj.domain.model.Attendance;
import com.moan.pet.moanHealthPrj.persistance.entity.AttendanceEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Component
public class AttendanceMapper {
    private final AttendanceMapStruct mapper;

    public Attendance convert(AttendanceEntity attendanceEntity) {
        return mapper.attendanceEntityToAttendance(attendanceEntity);
    }

    public List<Attendance> convert(List<AttendanceEntity> attendanceEntities) {
        return mapper.attendanceEntityToAttendance(attendanceEntities);
    }

    public Optional<Attendance> convert(Optional<AttendanceEntity> attendanceEntity) {
        return mapper.attendanceEntityToAttendance(attendanceEntity);
    }

    public AttendanceEntity convert(Attendance attendance) {
        return mapper.attendanceToAttendanceEntity(attendance);
    }
}
