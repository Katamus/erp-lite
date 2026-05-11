package com.criscahub.erp_lite.persistence.rabbit.dtos;

import com.criscahub.erp_lite.domain.entities.product.*;

import java.math.BigDecimal;
import java.time.Instant;

public record ProductCreatedMessage(
    String productId,
    String sku,
    String name,
    BigDecimal price,
    String currency,
    Instant timestamp,
    String description,
    Integer stock,
    String categoryId,
    String imageUrl,
    boolean active
) {



}
