package com.osama.ecommerceapplication.users;

import com.osama.ecommerceapplication.addresses.Address;
import com.osama.ecommerceapplication.addresses.AddressMapper;
import com.osama.ecommerceapplication.addresses.AddressRepository;
import com.osama.ecommerceapplication.exceptions.CustomExceptions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

    @Override
    @Transactional
    public UserResponseDTO getUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new CustomExceptions.UserNotFound(username));
        return UserMapper.toUserResponse(user);
    }


    @Override
    @Transactional
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        userRepository.findByUsername(userRequestDTO.getUsername())
                .ifPresent(user -> {
                    throw new CustomExceptions.UserAlreadyExistsException(userRequestDTO.getUsername());
                });
        userRepository.findByEmail(userRequestDTO.getEmail())
                .ifPresent(user -> {
                    throw new CustomExceptions.EmailAlreadyExistsException(userRequestDTO.getEmail());
                });
        Set<Address> addresses = userRequestDTO.getAddress().stream()
                .map(AddressMapper::toAddress)
                .collect(Collectors.toSet());
        User user = UserMapper.toUser(userRequestDTO);
        addressRepository.saveAll(addresses);

        user.setAddresses(addresses);
        User savedUser = userRepository.save(user);
        return UserMapper.toUserResponse(savedUser);
    }


    @Override
    @Transactional
    public UserResponseDTO updateUser(UserRequestDTO userRequestDTO) {
        return userRepository.findById(userRequestDTO.getId())
                .map(existingUser -> {
                    Optional.ofNullable(userRequestDTO.getUsername()).ifPresent(existingUser::setUsername);
                    Optional.ofNullable(userRequestDTO.getPassword()).ifPresent(existingUser::setPassword);
                    Optional.ofNullable(userRequestDTO.getEmail()).ifPresent(existingUser::setEmail);
                    existingUser.setUpdatedAt(LocalDateTime.now());
                    userRepository.save(existingUser);
                    return UserMapper.toUserResponse(existingUser);
                })
                .orElseThrow(() -> new CustomExceptions.UserNotFound(userRequestDTO.getId().toString()));
    }


    @Override
    @Transactional
    public Boolean deleteUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new CustomExceptions.UserNotFound(username));
        userRepository.delete(user);
        return true;
    }
}
