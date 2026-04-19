package com.criscahub.erp_lite.domain.repositories;

import com.criscahub.erp_lite.domain.customer.CustomerInfo;

import java.util.Optional;

/**
 * Port for external service for JSONPlaceholder
 */
public interface CustomerProvider {

    Optional<CustomerInfo> findById(Long Id);

    boolean existsById(Long id);

}
