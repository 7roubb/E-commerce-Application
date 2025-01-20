package com.osama.ecommerceapplication.addresses;


import java.util.List;

public interface AddressService {
    AddressResponseDTO createAddress(AddressRequestDTO addressRequestDTO);

    AddressResponseDTO updateAddress(AddressRequestDTO addressRequestDTO);

    Boolean deleteAddressByAddressIdAndUsers(Long addressId, Long userId);

    List<AddressResponseDTO> getAddressesByUserId(Long id);

    AddressResponseDTO getAddressById(Long id);
}
