package com.osama.ecommerceapplication.users;

public interface UserService {
    UserResponseDTO getUser(String username);
    UserResponseDTO createUser(UserRequestDTO userRequestDTO);
    UserResponseDTO updateUser(UserRequestDTO userRequestDTO);
    Boolean deleteUser(String username);
}
