package com.criscahub.erp_lite.domain.repositories;

import com.criscahub.erp_lite.domain.catalog.Catalog;
import com.criscahub.erp_lite.domain.catalog.CatalogItem;

import java.util.List;
import java.util.Optional;

/**
 * Port read-only for Catalog
 */
public interface CatalogRepository {

    Optional<Catalog> findByType(CatalogItem type);

    List<CatalogItem> findItemsByType(CatalogItem type);

    Optional<CatalogItem> findItemBtTypeAndCode(CatalogItem type, String code);

}
