package com.criscahub.erp_lite.persistence.mongo.repositories;

import com.criscahub.erp_lite.persistence.mongo.documents.AuditLogDocument;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuditLogRepository extends MongoRepository<AuditLogDocument, ObjectId> {
}
