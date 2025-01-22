package com.osama.ecommerceapplication.addresses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponseDTO {
    private Long addressId;
    private String street;
    private String city;
    private String country;
    private String state;
    private String zipcode;
    private String buildingName;
}