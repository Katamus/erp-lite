package com.criscahub.erp_lite.use_cases.product;

import com.criscahub.erp_lite.commands.product.CreateProductCommand;
import com.criscahub.erp_lite.domain.entities.product.*;
import com.criscahub.erp_lite.domain.ports.messages.EventPublisherPort;
import com.criscahub.erp_lite.domain.ports.repositories.ProductRepositoryPort;
import com.criscahub.erp_lite.domain.ports.services.ImageStorageServicePort;
import com.criscahub.erp_lite.domain.shared.Money;
import com.criscahub.erp_lite.exceptions.CommandException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Currency;

@Slf4j
@Service
@Transactional(noRollbackFor = RuntimeException.class)
@RequiredArgsConstructor
public class CreateProductUseCase {

    private final ProductRepositoryPort productRepository;

    private final ImageStorageServicePort imageStorageServicePort;

    private final EventPublisherPort eventPublisherPort;


    public String execute(CreateProductCommand command) {
        log.info("Creating product with SKU: {}", command.sku());

        try {
            // 1. Validate SKU uniqueness
            validateSkuUniqueness(command.sku());
            // 3. Create value objects
            SKU sku = SKU.of(command.sku());
            ProductName name = ProductName.of(command.name());
            Money price = Money.of(command.price(), Currency.getInstance(command.currency()));
            Stock stock = Stock.of(command.stock());
            CategoryReference category = CategoryReference.of(command.categoryId());
            ProductImage productImage = this.uploadImg(command);

            // 4. Create product aggregate
            ProductRoot product = ProductRoot.create(
                    sku,
                    name,
                    command.description(),
                    price,
                    stock,
                    category,
                    productImage,
                    command.createdBy()
            );

            log.debug("Product created in domain with ID: {}", product.getId().value());

            // 5. Persist product
            ProductRoot savedProduct = productRepository.save(product);

            log.info("Product persisted with ID: {}", savedProduct.getId().value());

            this.sendEventMessage(product);

            return savedProduct.getId().value().toString();

        } catch (IllegalArgumentException iae) {
            log.error("Invalid data for product creation");
            throw new CommandException("Error creating product: " + iae.getMessage());
        } catch (Exception e) {
            log.error("Unexpected error creating product", e);
            throw new CommandException("Failed to create product: " + e.getMessage());
        }
    }

    private @Nullable ProductImage uploadImg(CreateProductCommand command) {
        if( !command.hasImage() ){
            log.info("Product image is empty");
        }
        log.info("Uploading image with SKU:{}", command.sku());
        try {
            return imageStorageServicePort.upload(command.imageName(), command.imageData());
        }catch (Exception e){
            log.error("Unexpected error uploading image with SKU ", e);
            throw new CommandException("Error uploading image with SKU:"+e.getMessage());
        }


    }

    private void validateSkuUniqueness(String sku) {
        log.debug("Validating SKU uniqueness: {}", sku);

        if (productRepository.findBySku(sku).isPresent()) {
            log.warn("SKU already exists: {}", sku);
            throw new CommandException("Product with SKU '" + sku + "' already exists");
        }
    }

    private void sendEventMessage(ProductRoot productSaved){

        productSaved.getDomainEvents().forEach(eventPublisherPort::publish);
        productSaved.clearDomainEvents();
        log.info("Event send successfully");

    }
}
