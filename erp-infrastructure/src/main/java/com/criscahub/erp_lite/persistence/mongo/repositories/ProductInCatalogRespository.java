package com.criscahub.erp_lite.persistence.mongo.repositories;

import com.criscahub.erp_lite.persistence.mongo.documents.ProductInCatalogDocument;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ProductInCatalogRespository extends MongoRepository<ProductInCatalogDocument,String> {
}
