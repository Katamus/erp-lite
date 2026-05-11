package com.criscahub.erp_lite.domain.entities.product.events;

import com.criscahub.erp_lite.domain.common.DomainEvent;
import com.criscahub.erp_lite.domain.entities.product.*;
import com.criscahub.erp_lite.domain.shared.Money;

import java.time.Instant;

/**
 * Emitted when a new product is created. TRIGGERS sync to MongoDB (CQRS).
 */
public record ProductCreated(
        ProductId productId,
        SKU sku,
        ProductName name,
        Money price,
        Instant timestamp,
        String description,
        Stock stock,
        CategoryReference category,
        ProductImage image,
        boolean active
) implements DomainEvent {
}
