package com.criscahub.erp_lite.queries;

import com.criscahub.erp_lite.domain.ports.repositories.ProductCatalogRepositoryPort;
import com.criscahub.erp_lite.domain.views.ProductView;
import com.criscahub.erp_lite.exceptions.QueryException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class FindProductByIdQuery {

    private final ProductCatalogRepositoryPort productCatalogRepository;

    public Optional<ProductView> execute(String id) {
        log.info("Execute FindProductByIdQuery id");
        try {
            return productCatalogRepository.findById(id);
        }catch (RuntimeException e){
            throw new QueryException("Error executing ProductCatalogRepositoryPort");
        }

    }

}
