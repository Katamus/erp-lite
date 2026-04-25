package com.criscahub.erp_lite.persistence.rest.mappers;

import com.criscahub.erp_lite.domain.customer.CustomerInfo;
import com.criscahub.erp_lite.persistence.rest.dtos.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface CustomerMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "phone", source = "phone")

    @Mapping(target = "address", expression = "java(buildAddress(user))")

    @Mapping(target = "city", expression = "java(getCity(user))")
    @Mapping(target = "zipcode", expression = "java(getZipcode(user))")

    @Mapping(target = "companyName", expression = "java(getCompanyName(user))")

    CustomerInfo toCustomerInfo(UserDTO user);

    // ---------------- HELPERS ----------------

    default String buildAddress(UserDTO user) {
        if (user == null || user.address() == null) {
            return null;
        }

        String street = user.address().street();
        String suite = user.address().suite();

        if (street == null && suite == null) {
            return null;
        }

        if (suite == null || suite.isBlank()) {
            return street;
        }

        if (street == null || street.isBlank()) {
            return suite;
        }

        return street + ", " + suite;
    }

    default String getCity(UserDTO user) {
        return (user != null && user.address() != null)
                ? user.address().city()
                : null;
    }

    default String getZipcode(UserDTO user) {
        return (user != null && user.address() != null)
                ? user.address().zipcode()
                : null;
    }

    default String getCompanyName(UserDTO user) {
        return (user != null && user.company() != null)
                ? user.company().name()
                : null;
    }
}