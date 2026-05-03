package com.criscahub.erp_lite.domain.entities.product.events;

import com.criscahub.erp_lite.domain.common.DomainEvent;
import com.criscahub.erp_lite.domain.entities.product.ProductId;
import com.criscahub.erp_lite.domain.entities.product.SKU;
import com.criscahub.erp_lite.domain.entities.product.ProductName;
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
    Instant timestamp
) implements DomainEvent {
}
