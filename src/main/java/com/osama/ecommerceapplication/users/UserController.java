package com.osama.ecommerceapplication.users;

import com.osama.ecommerceapplication.common.ApiResponse;
import com.osama.ecommerceapplication.common.OnCreate;
import com.osama.ecommerceapplication.common.OnUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final MessageSource messageSource;

    @GetMapping
    public ApiResponse<UserResponseDTO> getUserByUserName(@RequestParam String username) {
        UserResponseDTO userResponse = userService.getUser(username);
        return ApiResponse.success(userResponse, HttpStatus.OK, getMessage("user.get.success", username));
    }

    @PostMapping
    public ApiResponse<UserResponseDTO> createUser(@Validated(OnCreate.class) @RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDTO userResponse = userService.createUser(userRequestDTO);
        return ApiResponse.success(userResponse, HttpStatus.OK, getMessage("user.create.success", userRequestDTO.getUsername()));

    }


    @PutMapping
    public ApiResponse<UserResponseDTO> updateUser(@Validated(OnUpdate.class) @RequestBody UserRequestDTO userRequest) {
        UserResponseDTO userResponse = userService.updateUser(userRequest);
        return ApiResponse.success(userResponse, HttpStatus.OK, getMessage("user.update.success", userRequest.getUsername()));
    }

    @DeleteMapping
    public ApiResponse<Boolean> deleteUser(@RequestParam String username) {
        Boolean flag = userService.deleteUser(username);
        return ApiResponse.success(flag, HttpStatus.OK, getMessage("user.delete.success", username));
    }

    public String getMessage(String code , String message) {
        return messageSource.getMessage(code, new Object[]{message}, LocaleContextHolder.getLocale());
    }
}
