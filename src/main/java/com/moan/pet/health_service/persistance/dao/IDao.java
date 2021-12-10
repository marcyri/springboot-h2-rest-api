package com.moan.pet.health_service.persistance.dao;

import java.util.List;
import java.util.Optional;

public interface IDao<T, ID> {
    List<T> findAll();
    Optional<T> findById(ID id);
    List<T> findByAttendances_Id(Long attendanceId);
    T save(T var1);
    T getById(ID id);
}
