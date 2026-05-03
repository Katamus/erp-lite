package com.criscahub.erp_lite.domain.entities.order.events;

import com.criscahub.erp_lite.domain.common.DomainEvent;
import com.criscahub.erp_lite.domain.entities.order.OrderId;
import com.criscahub.erp_lite.domain.shared.CustomerId;
import com.criscahub.erp_lite.domain.shared.Money;

import java.time.Instant;

/**
 * Emitted when a new order is created.
 */
public record OrderCreated(
    OrderId orderId,
    CustomerId customerId,
    String customerName,
    Money totalAmount,
    Instant timestamp
) implements DomainEvent {
}
