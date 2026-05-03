package com.criscahub.erp_lite.persistence.mongo.mappers;


import com.criscahub.erp_lite.domain.views.ProductView;
import com.criscahub.erp_lite.persistence.mongo.documents.ProductInCatalogDocument;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface ProductCatalogMapper {

    @Mapping(source = "currency", target = "money")
    ProductView toView(ProductInCatalogDocument document);
}
