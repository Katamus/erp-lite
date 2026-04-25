package com.criscahub.erp_lite.persistence.rest.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CompanyDTO(
        String name,
        @JsonProperty("catchPhrase") String catchPhrase,
        String bs
) {}