package com.moan.pet.moanHealthPrj.model.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @Column(updatable = false, nullable = false)
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    private Long id;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdDtTm;

    @UpdateTimestamp
    private LocalDateTime updatedDtTm;

//    @PrePersist
//    protected void onCreate() {
//        if (this.createdDtTm == null) createdDtTm = LocalDateTime.now();
//        if (this.updatedDtTm == null) updatedDtTm = LocalDateTime.now();
//    }
//
//    @PreUpdate
//    protected void onUpdate() {
//        updatedDtTm = LocalDateTime.now();
//    }
}
