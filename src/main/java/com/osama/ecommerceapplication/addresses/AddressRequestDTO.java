package com.osama.ecommerceapplication.addresses;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AddressRequestDTO {

    @NotBlank(message = "{street.isRequired}")
    @Size(min = 3, max = 100, message = "{street.size}")
    private String street;

    @NotBlank(message = "{city.isRequired}")
    @Size(min = 2, max = 50, message = "{city.size}")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "{city.pattern}")
    private String city;

    @NotBlank(message = "{country.isRequired}")
    @Size(min = 2, max = 50, message = "{country.size}")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "{country.pattern}")
    private String country;

    @NotBlank(message = "{state.isRequired}")
    @Size(min = 2, max = 50, message = "{state.size}")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "{state.pattern}")
    private String state;

    @NotBlank(message = "{zip.isRequired}")
    @Pattern(regexp = "^\\d{5}(-\\d{4})?$", message = "{zip.pattern}")
    private String zipcode;

    @NotBlank(message = "{building.isRequired}")
    @Size(max = 100, message = "{building.size}")
    @Pattern(regexp = "^[a-zA-Z0-9\\s.,'-]+$", message = "{building.pattern}")
    private String buildingName;
}
