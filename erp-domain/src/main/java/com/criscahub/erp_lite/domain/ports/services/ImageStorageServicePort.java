package com.criscahub.erp_lite.domain.ports.services;

import com.criscahub.erp_lite.domain.entities.product.ProductImage;

/**
 *  Port for storage S3 files
 */
public interface ImageStorageServicePort {

    ProductImage upload(String imageName, byte[] imageData);

    void delete(ProductImage img);

    byte[] download(ProductImage img);

}
