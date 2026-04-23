package com.criscahub.erp_lite.domain.ports;

import com.criscahub.erp_lite.domain.product.ProductImage;

public interface ImageStorageService {

    ProductImage upload(String imageName, byte[] imageData);

    void delete(ProductImage img);

    byte[] download(ProductImage img);

}
