package com.criscahub.erp_lite.commands.product;

import jakarta.validation.constraints.NotBlank;

public record DesactivateProductCommand(
        @NotBlank(message = "Product ID cannot be null or blank")
        String productId
) {

}
