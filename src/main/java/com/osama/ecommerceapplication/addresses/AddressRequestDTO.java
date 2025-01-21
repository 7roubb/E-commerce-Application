package com.osama.ecommerceapplication.addresses;

import com.osama.ecommerceapplication.common.OnCreate;
import com.osama.ecommerceapplication.common.OnUpdate;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Builder
@Validated
@Valid
public class AddressRequestDTO {

    @NotBlank(message = "{street.isRequired}", groups = {OnCreate.class})
    @Size(min = 3, max = 100, message = "{street.size}", groups = {OnCreate.class,OnUpdate.class})
    private String street;

    @NotBlank(message = "{city.isRequired}", groups = {OnCreate.class})
    @Size(min = 2, max = 50, message = "{city.size}", groups = {OnCreate.class})
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "{city.pattern}", groups = {
            OnCreate.class,OnUpdate.class})
    private String city;

    @NotBlank(message = "{country.isRequired}", groups = {OnCreate.class})
    @Size(min = 2, max = 50,
            message = "{country.size}",
            groups = {OnCreate.class,OnUpdate.class})
    @Pattern(regexp = "^[a-zA-Z\\s]+$",
            message = "{country.pattern}",
            groups = {OnCreate.class,OnUpdate.class})
    private String country;

    @NotBlank(message = "{state.isRequired}", groups = {OnCreate.class})
    @Size(min = 2, max = 50,
            message = "{state.size}", groups = {OnCreate.class})
    @Pattern(regexp = "^[a-zA-Z\\s]+$",
            message = "{state.pattern}",
            groups = {OnCreate.class,OnUpdate.class})
    private String state;

    @NotBlank(message = "{zip.isRequired}", groups = {OnCreate.class})
    @Pattern(regexp = "^\\d{5}(-\\d{4})?$",
            message = "{zip.pattern}", groups = {OnCreate.class,OnUpdate.class})
    private String zipcode;

    @NotBlank(message = "{building.isRequired}", groups = {OnCreate.class})
    @Size(max = 100,
            message = "{building.size}",
            groups = {OnCreate.class ,OnUpdate.class})
    @Pattern(regexp = "^[a-zA-Z0-9\\s.,'-]+$",
            message = "{building.pattern}",
            groups = {OnCreate.class,OnUpdate.class})
    private String buildingName;

    @NotBlank(groups = {OnUpdate.class})
    @Positive
    private Long addressId;

}
