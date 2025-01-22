package com.osama.ecommerceapplication.users;

import com.osama.ecommerceapplication.addresses.AddressRequestDTO;
import com.osama.ecommerceapplication.common.OnCreate;
import com.osama.ecommerceapplication.common.OnUpdate;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.validation.annotation.Validated;
import java.util.Set;

@Data
@Validated
@Valid
public class UserRequestDTO {

    @NotNull(groups = {OnUpdate.class},
            message = "{user.id.notnull}")
    @Positive(message = "{user.id.positive}")
    private Long id;

    @NotBlank(groups = {OnCreate.class},
            message = "{user.username.required}")
    @Pattern(regexp = "^[a-zA-Z0-9]{3,20}$", message = "{user.username.invalid}",
            groups = {OnCreate.class, OnUpdate.class})
    private String username;

    @NotBlank(message = "{user.email.required}",
            groups = {OnCreate.class})
    @Email(groups = {OnCreate.class}, message = "{user.email.invalid}")
    @Pattern(regexp = "^[\\w.-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$",
            message = "{user.email.pattern.invalid}",
            groups = {OnCreate.class ,OnUpdate.class})
    private String email;

    @NotBlank(groups = {OnCreate.class},
            message = "{user.password.required}")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "{user.password.weak}",
            groups = {OnCreate.class,OnUpdate.class}
    )
    private String password;

    @NotBlank(groups = {OnCreate.class},
            message = "{user.fullName.required}")
    @Pattern(regexp = "^[A-Za-z\s'-]{1,50}$",groups = {OnCreate.class,OnUpdate.class})
    private String fullName;

    @NotNull(groups = {OnCreate.class})
    @Valid
    private Set<AddressRequestDTO> address;


}
