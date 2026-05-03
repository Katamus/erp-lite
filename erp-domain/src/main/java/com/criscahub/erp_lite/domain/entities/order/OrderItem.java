package com.criscahub.erp_lite.domain.entities.order;

import com.criscahub.erp_lite.domain.common.Entity;
import com.criscahub.erp_lite.domain.entities.product.ProductRoot;
import com.criscahub.erp_lite.domain.entities.product.ProductId;
import com.criscahub.erp_lite.domain.shared.Money;
import com.criscahub.erp_lite.domain.shared.Quantity;
import lombok.Getter;

/**
 * Order line item. Snapshots product name and price at creation time.
 */
@Getter
public class OrderItem extends Entity<OrderItemId> {

    private ProductId productReference;
    private String productName;   // Snapshot at order creation
    private Quantity quantity;
    private Money unitPrice;      // Snapshot at order creation
    private Money subtotal;       // quantity * unitPrice

    public static OrderItem from(ProductRoot product, Quantity quantity) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        if (quantity == null) {
            throw new IllegalArgumentException("Quantity cannot be null");
        }

        if (!product.isActive()) {
            throw new IllegalArgumentException("Cannot create order item for inactive product: " + product.getSku().value());
        }
        if (!product.hasAvailableStock(quantity.value())) {
            throw new IllegalArgumentException(
                    "Insufficient stock for product " + product.getSku().value() +
                            ". Required: " + quantity.value() + ", Available: " + product.getStock().value()
            );
        }

        Money unitPrice = product.getPrice();
        Money subtotal  = unitPrice.multiply(quantity);
        return new OrderItem(
            OrderItemId.generate(),
            product.getId(),
            product.getName().value(),
            quantity,
            unitPrice,
            subtotal
        );
    }

    private OrderItem(
            OrderItemId id,
            ProductId productReference,
            String productName,
            Quantity quantity,
            Money unitPrice,
            Money subtotal
    ) {
        super(id);
        this.productReference = productReference;
        this.productName = productName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.subtotal = subtotal;
    }

    public Money calculateSubtotal() {
        return unitPrice.multiply(quantity);
    }
}
