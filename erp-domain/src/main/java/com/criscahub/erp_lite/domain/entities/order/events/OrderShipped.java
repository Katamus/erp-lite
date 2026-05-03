package com.criscahub.erp_lite.domain.entities.order.events;

import com.criscahub.erp_lite.domain.common.DomainEvent;
import com.criscahub.erp_lite.domain.entities.order.OrderId;

import java.time.Instant;

/**
 * Emitted when order transitions CONFIRMED -> SHIPPED.
 */
public record OrderShipped(
    OrderId orderId,
    Instant timestamp
) implements DomainEvent {
}
