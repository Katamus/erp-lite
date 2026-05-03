package com.criscahub.erp_lite.domain.entities.order.events;

import com.criscahub.erp_lite.domain.common.DomainEvent;
import com.criscahub.erp_lite.domain.entities.order.OrderId;

import java.time.Instant;

/**
 * Emitted when order is cancelled. If was CONFIRMED, stock must be released.
 */
public record OrderCancelled(
    OrderId orderId,
    String reason,
    Instant timestamp
) implements DomainEvent {
}
