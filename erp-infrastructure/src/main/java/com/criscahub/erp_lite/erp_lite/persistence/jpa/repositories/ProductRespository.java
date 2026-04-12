package com.criscahub.erp_lite.erp_lite.persistence.jpa.repositories;

import com.criscahub.erp_lite.erp_lite.persistence.jpa.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRespository extends JpaRepository<ProductEntity, UUID> {
}
