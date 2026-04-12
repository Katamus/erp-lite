package com.criscahub.erp_lite.erp_lite.persistence.mongo.documents;

import java.util.Map;

public record CatalogItem(
        String id,
        String code,
        String value,
        String description,
        Integer displayOrder,
        Map<String, Object> metadata
) {}