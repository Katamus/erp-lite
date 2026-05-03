package com.criscahub.erp_lite.domain.ports.repositories;

import com.criscahub.erp_lite.domain.entities.product.ProductRoot;
import com.criscahub.erp_lite.domain.entities.product.ProductId;

import java.util.Optional;

/**
 *  Port for Storage o consult Products
 */
public interface ProductRepositoryPort {

    ProductRoot save(ProductRoot product);

    Optional<ProductRoot> findById(ProductId ID);

    Optional<ProductRoot> findBySku(String sku);

    void delete(ProductRoot product);

}
