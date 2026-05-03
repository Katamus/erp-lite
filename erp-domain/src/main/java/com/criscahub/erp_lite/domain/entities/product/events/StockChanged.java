package com.criscahub.erp_lite.domain.entities.product.events;

import com.criscahub.erp_lite.domain.common.DomainEvent;
import com.criscahub.erp_lite.domain.entities.product.ProductId;

import java.time.Instant;

/**
 * Emitted when stock changes (increment or decrement). TRIGGERS sync to MongoDB.
 */
public record StockChanged(
    ProductId productId,
    Integer oldStock,
    Integer newStock,
    String reason,
    Instant timestamp
) implements DomainEvent {
}
