package com.criscahub.erp_lite.persistence.rest.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "jsonplaceholder.api")
@Validated
public record JsonplaceholderConfigModel(

        @NotBlank(message = "El baseUrl no puede estar vacío")
        @Pattern(regexp = "https?://.*", message = "El baseUrl debe ser una URL válida")
        String baseUrl,

        @NotBlank(message = "El usersEndpoint no puede estar vacío")
        String usersEndpoint,

        @Min(value = 1, message = "connectTimeout debe ser mayor a 0")
        int connectTimeout,

        @Min(value = 1, message = "readTimeout debe ser mayor a 0")
        int readTimeout,

        @NotNull(message = "enabled no puede ser null")
        Boolean enabled

) {

    public String getUsersUrl() {
        return baseUrl + usersEndpoint;
    }
}