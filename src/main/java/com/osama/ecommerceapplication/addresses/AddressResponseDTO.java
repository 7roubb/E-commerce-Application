package com.osama.ecommerceapplication.addresses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressResponseDTO {
    private Long addressId;
    private String street;
    private String city;
    private String country;
    private String state;
    private String zipcode;
    private String buildingName;
}