package com.moan.pet.health_service.domain.model;

import lombok.Data;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
public abstract class BaseDomainModel {

    private Long id;

    private LocalDateTime createdDtTm;

    private LocalDateTime updatedDtTm;
}
