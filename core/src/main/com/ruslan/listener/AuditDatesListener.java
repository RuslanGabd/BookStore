package com.ruslan.listener;

import com.ruslan.entity.AuditableEntity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.Instant;

public class AuditDatesListener {
    @PrePersist
    public void prePersist(AuditableEntity<?> entity) {
        entity.setCreatedAt(Instant.now());
//        setCreatedBy(SecurityContext.getUser());
    }

    @PreUpdate
    public void preUpdate(AuditableEntity<?> entity) {
    //    entity.setUpdatedAt(Instant.now());
//        setUpdatedBy(SecurityContext.getUser());
    }
}
