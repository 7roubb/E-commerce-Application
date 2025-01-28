package com.osama.ecommerceapplication.addresses;


import com.osama.ecommerceapplication.common.ApiResponse;
import com.osama.ecommerceapplication.common.OnCreate;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;
    private final MessageSource messageSource;

    @PostMapping
    public ApiResponse<AddressResponseDTO> addAddress(@Validated(OnCreate.class) @RequestBody AddressRequestDTO address) {
        AddressResponseDTO responseDTO = addressService.createAddress(address);
        return ApiResponse.success(
                responseDTO,
                HttpStatus.OK,
                getMessage("address.create.success")
        );
    }
    @GetMapping("/all")
    public ApiResponse<List<AddressResponseDTO>> getAddressesByUserId(@RequestHeader Long userId) {
        List<AddressResponseDTO> addresses = addressService.getAddressesByUserId(userId);
        return ApiResponse.success(
                addresses,
                HttpStatus.OK,
                getMessage("address.get.success")
        );
    }

    @GetMapping
    public ApiResponse<AddressResponseDTO> getAddressById(@RequestHeader Long addressId) {
        AddressResponseDTO responseDTO = addressService.getAddressById(addressId);
        return ApiResponse.success(
                responseDTO,
                HttpStatus.OK,
                getMessage("address.get.success")
        );
    }

    @PutMapping
    public ApiResponse<AddressResponseDTO> updateAddress(@RequestBody AddressRequestDTO address) {
        AddressResponseDTO responseDTO = addressService.updateAddress(address);
        return ApiResponse.success(
                responseDTO,
                HttpStatus.OK,
                getMessage("address.update.success")
        );
    }

    @DeleteMapping
    public ApiResponse<Boolean> deleteAddress(@RequestHeader Long addressId , @RequestHeader Long userId) {
        addressService.deleteAddressByAddressIdAndUsers(addressId, userId);
        return ApiResponse.success(
                null,
                HttpStatus.OK,
                getMessage("address.delete.success")
        );
    }

    public String getMessage(String code) {
        return messageSource.
                getMessage(code, new Object[]{null}, LocaleContextHolder.getLocale());
    }
}
