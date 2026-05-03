package com.criscahub.erp_lite.domain.shared;

import java.time.Instant;

/**
 * Audit information for aggregates.
 */
public record AuditInfo(String createdBy, Instant createdAt, Instant updatedAt) {

    public AuditInfo {
        if (createdBy == null || createdBy.isBlank()) {
            throw new IllegalArgumentException("CreatedBy cannot be null or blank");
        }
        if (createdAt == null) {
            createdAt = Instant.now();
        }
        if (updatedAt == null) {
            updatedAt= createdAt;
        }
    }

    public static AuditInfo create(String createdBy, Instant timestamp) {
        return new AuditInfo(createdBy, timestamp, timestamp);
    }

    public AuditInfo updateTimestamp() {
        return new AuditInfo(this.createdBy, this.createdAt, Instant.now());
    }
}
