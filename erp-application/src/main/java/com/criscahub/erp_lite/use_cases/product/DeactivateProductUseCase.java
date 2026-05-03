package com.criscahub.erp_lite.use_cases.product;

import com.criscahub.erp_lite.commands.product.DesactivateProductCommand;
import com.criscahub.erp_lite.domain.entities.product.ProductId;
import com.criscahub.erp_lite.domain.entities.product.ProductRoot;
import com.criscahub.erp_lite.domain.ports.repositories.ProductRepositoryPort;
import com.criscahub.erp_lite.exceptions.CommandException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class DeactivateProductUseCase {


    private final ProductRepositoryPort productRepository;

    public void execute(DesactivateProductCommand command) {
        log.info("Deactivating product: {}", command.productId());

        try {
            // 1. Find product
            ProductRoot product = findProductById(command.productId());

            log.debug("Current status: active={}", product.isActive());

            // 2. Deactivate product
            product.deactivate();

            log.debug("Product deactivated in domain");

            // 3. Persist changes
            productRepository.save(product);

            log.info("Product deactivation persisted");

        } catch (IllegalStateException ise) {
            log.error("Product already deactivated", ise);
            throw new CommandException("Product is already deactivated");
        } catch (Exception e) {
            log.error("Unexpected error deactivating product", e);
            throw new CommandException("Failed to deactivate product: " + e.getMessage());
        }
    }

    private ProductRoot findProductById(String productId) {
        log.debug("Finding product by ID: {}", productId);

        ProductId productIdVO = ProductId.of(UUID.fromString(productId));

        return productRepository.findById(productIdVO)
                .orElseThrow(() -> {
                    log.warn("Product not found: {}", productId);
                    return new CommandException("Product not found with ID: " + productId);
                });
    }
}
