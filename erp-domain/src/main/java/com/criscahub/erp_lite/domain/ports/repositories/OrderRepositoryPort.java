package com.criscahub.erp_lite.domain.ports.repositories;

import com.criscahub.erp_lite.domain.entities.order.OrderId;
import com.criscahub.erp_lite.domain.entities.order.OrderNumber;
import com.criscahub.erp_lite.domain.entities.order.OrderRoot;
import com.criscahub.erp_lite.domain.entities.product.ProductId;
import com.criscahub.erp_lite.domain.shared.CustomerId;

import java.util.List;
import java.util.Optional;


/**
 *  Port for Storage o consult Orders
 */
public interface OrderRepositoryPort {

    OrderRoot save(OrderRoot order);

    Optional<OrderRoot> findById(OrderId id);

    Optional<OrderRoot> findAllByOrderNumber(OrderNumber orderNumber);

    List<OrderRoot> findByCustomerId(CustomerId customerId);

    void delete(OrderRoot order);

}
