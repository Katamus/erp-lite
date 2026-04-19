package com.criscahub.erp_lite.domain.order;

import java.time.Year;
import java.util.regex.Pattern;

/**
 * Unique order number.
 * Pattern: ORD-2025-001
 */
public record OrderNumber(String value) {

    private static final Pattern PATTERN = Pattern.compile("ORD-\\d{4}-\\d{3}");

    public OrderNumber {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("OrderNumber cannot be null or blank");
        }
        if (!PATTERN.matcher(value).matches()) {
            throw new IllegalArgumentException(
                "OrderNumber must match pattern: ORD-YYYY-NNN (e.g. ORD-2025-001)"
            );
        }
    }

    public static OrderNumber of(String value) {
        return new OrderNumber(value);
    }

    public static OrderNumber generate() {
        int seq = (int) (Math.random() * 900) + 100;
        return new OrderNumber("ORD-" + Year.now().getValue() + "-" + String.format("%03d", seq));
    }
}
