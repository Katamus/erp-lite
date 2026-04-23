package com.criscahub.erp_lite.persistence.aws.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "aws.s3")
@Validated
public record AwsConfigModel(

    @NotBlank(message = "El endpoint de S3 no puede estar vacío")
    String endpoint,

    @NotBlank(message = "La región no puede estar vacía")
    String region,

    @NotBlank(message = "El access key no puede estar vacío")
    String accessKey,

    @NotBlank(message = "El secret key no puede estar vacío")
    String secretKey,

    @NotBlank(message = "El nombre del bucket no puede estar vacío")
    String bucketName,

    @NotNull(message = "pathStyleEnabled no puede ser null")
    Boolean pathStyleEnabled

) {

    public String getBucketUrl(){
        return String.format("%s/%s",endpoint,bucketName);
    }

}
