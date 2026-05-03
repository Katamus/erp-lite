package com.criscahub.erp_lite.domain.ports.repositories;

import com.criscahub.erp_lite.domain.entities.catalog.CatalogRoot;
import com.criscahub.erp_lite.domain.entities.catalog.CatalogItem;

import java.util.List;
import java.util.Optional;

/**
 *  Port for Storage o consult Catalog
 */
public interface CatalogRepositoryPort {

    Optional<CatalogRoot> findByType(CatalogItem type);

    List<CatalogItem> findItemsByType(CatalogItem type);

    Optional<CatalogItem> findItemBtTypeAndCode(CatalogItem type, String code);

}
