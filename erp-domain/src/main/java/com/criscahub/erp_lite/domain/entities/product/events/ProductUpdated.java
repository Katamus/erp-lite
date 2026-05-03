package com.criscahub.erp_lite.domain.entities.product.events;

import com.criscahub.erp_lite.domain.common.DomainEvent;
import com.criscahub.erp_lite.domain.entities.product.ProductId;

import java.time.Instant;

/**
 * Emitted when product info is updated. TRIGGERS sync to MongoDB.
 */
public record ProductUpdated(
    ProductId productId,
    Instant timestamp
) implements DomainEvent {
}
