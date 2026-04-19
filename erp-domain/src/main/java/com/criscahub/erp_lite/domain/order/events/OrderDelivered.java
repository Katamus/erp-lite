package com.criscahub.erp_lite.domain.order.events;

import com.criscahub.erp_lite.domain.common.DomainEvent;
import com.criscahub.erp_lite.domain.order.OrderId;

import java.time.Instant;

/**
 * Emitted when order transitions SHIPPED -> DELIVERED. Final state.
 */
public record OrderDelivered(
    OrderId orderId,
    Instant timestamp
) implements DomainEvent {
}
