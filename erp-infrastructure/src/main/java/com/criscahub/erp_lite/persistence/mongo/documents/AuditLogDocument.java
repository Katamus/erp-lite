package com.criscahub.erp_lite.persistence.mongo.documents;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "audit_logs")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditLogDocument {

    @Id
    private ObjectId id;

    private String className;

    private String methodName;

    private String endpoint;

    private String userId;

    private String ipAddress;

    private Boolean success;

    private String errorMessage;

    private Long executionTimeMs;

    private Instant timestamp;
}