package com.criscahub.erp_lite.domain.product.events;

import com.criscahub.erp_lite.domain.common.DomainEvent;
import com.criscahub.erp_lite.domain.product.ProductId;

import java.time.Instant;

/**
 * Emitted when product is deactivated. TRIGGERS sync to MongoDB.
 */
public record ProductDeactivated(
    ProductId productId,
    Instant timestamp
) implements DomainEvent {
}
