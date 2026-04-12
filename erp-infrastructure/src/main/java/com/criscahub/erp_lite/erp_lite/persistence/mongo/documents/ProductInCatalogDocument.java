package com.criscahub.erp_lite.erp_lite.persistence.mongo.documents;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Document(collection = "product_documents")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductInCatalogDocument {

    @Id
    private String id;

    private Boolean active;

    private String categoryId;

    private String categoryName;

    private String name;

    private String description;

    private String sku;

    private BigDecimal price;

    private String currency;

    private Integer stock;

    private String imageUrl;

    private Map<String, String> specifications;

    private List<String> tags;

    private Instant createdAt;

    private Instant updatedAt;
}
