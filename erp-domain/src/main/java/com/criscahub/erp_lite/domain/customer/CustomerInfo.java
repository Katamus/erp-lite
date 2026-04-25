package com.criscahub.erp_lite.domain.customer;


/**
 * Value object inmutable for JSONPLaceholder API
 * @param id
 * @param name
 * @param email
 * @param phone
 * @param address
 * @param city
 * @param zipcode
 * @param companyName
 */
public record CustomerInfo (
        Long id,
        String name,
        String email,
        String phone,
        String address,
        String city,
        String zipcode,
        String companyName
){
    public CustomerInfo{
        if (id == null){
            throw new IllegalArgumentException("id is not present");
        }

        if (name == null || name.isBlank()){
            throw new IllegalArgumentException("name is not present");
        }
    }
}
