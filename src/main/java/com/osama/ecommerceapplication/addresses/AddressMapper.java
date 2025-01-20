package com.osama.ecommerceapplication.addresses;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class AddressMapper {

    public static Address toAddress(AddressRequestDTO addressRequest) {
        return Optional.ofNullable(addressRequest).map(req -> {
            Address address = new Address();
            address.setStreet(req.getStreet());
            address.setCity(req.getCity());
            address.setCountry(req.getCountry());
            address.setState(req.getState());
            address.setZipcode(req.getZipcode());
            address.setBuildingName(req.getBuildingName());
            return address;
        }).orElse(null);
    }

    public static AddressResponseDTO toAddressResponse(Address address) {
        return Optional.ofNullable(address).map(addr -> {
            AddressResponseDTO response = new AddressResponseDTO();
            response.setAddressId(addr.getAddressId());
            response.setStreet(addr.getStreet());
            response.setCity(addr.getCity());
            response.setCountry(addr.getCountry());
            response.setState(addr.getState());
            response.setZipcode(addr.getZipcode());
            response.setBuildingName(addr.getBuildingName());
            return response;
        }).orElse(null);
    }
}
