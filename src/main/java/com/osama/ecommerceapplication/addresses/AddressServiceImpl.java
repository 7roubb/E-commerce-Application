package com.osama.ecommerceapplication.addresses;

import com.osama.ecommerceapplication.exceptions.CustomExceptions;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Override
    public AddressResponseDTO createAddress(AddressRequestDTO addressRequestDTO) {
        Address address = AddressMapper.toAddress(addressRequestDTO);
        Address savedAddress = addressRepository.save(address);
        return AddressMapper.toAddressResponse(savedAddress);
    }

    @Override
    public AddressResponseDTO updateAddress(AddressRequestDTO addressRequestDTO) {
        
        Address existingAddress = addressRepository.findById(addressRequestDTO.getAddressId())
                .orElseThrow(() -> new CustomExceptions.AddressNotFound());
        Optional.ofNullable(addressRequestDTO.getStreet())
                .ifPresent(existingAddress::setStreet);
        Optional.ofNullable(addressRequestDTO.getCity())
                .ifPresent(existingAddress::setCity);
        Optional.ofNullable(addressRequestDTO.getZipcode())
                .ifPresent(existingAddress::setZipcode);
        Optional.ofNullable(addressRequestDTO.getCountry())
                .ifPresent(existingAddress::setCountry);
        Optional.ofNullable(addressRequestDTO.getBuildingName())
                .ifPresent(existingAddress::setBuildingName);
        Optional.ofNullable(addressRequestDTO.getState())
                .ifPresent(existingAddress::setState);

        Address updatedAddress = addressRepository.save(existingAddress);
        return AddressMapper.toAddressResponse(updatedAddress);
    }

    @Override
    public Boolean deleteAddressByAddressIdAndUsers(Long addressId, Long userId) {
        return addressRepository.
                deleteAddressByAddressIdAndUsers_Id(addressId, userId);
    }

    @Override
    public List<AddressResponseDTO> getAddressesByUserId(Long userId) {
        List<Address> addresses = addressRepository.
                findAllByUsers_Id(userId)
                .orElseThrow(
                        () -> new CustomExceptions.AddressNotFound());

        return addresses.stream()
                .map(AddressMapper::toAddressResponse)
                .collect(Collectors.toList());
    }
    @Override
    public AddressResponseDTO getAddressById(Long id) {
        Address existingAddress = addressRepository.
                getAddressByAddressId(id)
                .orElseThrow(
                        () -> new CustomExceptions.AddressNotFound());
        return AddressMapper.
                toAddressResponse(existingAddress);
    }



}
