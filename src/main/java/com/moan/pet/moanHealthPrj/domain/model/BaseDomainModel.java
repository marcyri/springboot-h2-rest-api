package com.moan.pet.moanHealthPrj.domain.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
public abstract class BaseDomainModel {

    private Long id;

    private LocalDateTime createdDtTm;

    private LocalDateTime updatedDtTm;
}
