package com.bikertribe.harley_events.service;

import com.bikertribe.harley_events.dto.UserRegistrationDto;
import com.bikertribe.harley_events.dto.UserResponseDto;
import com.bikertribe.harley_events.model.Role;
import com.bikertribe.harley_events.model.User;
import com.bikertribe.harley_events.exception.UserAlreadyExistsException;
import com.bikertribe.harley_events.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponseDto registerUser(UserRegistrationDto registrationDto) {
        if (userRepository.existsByUsername(registrationDto.username())) {
            throw new UserAlreadyExistsException("Username is already taken");
        }

        if (userRepository.existsByEmail(registrationDto.email())) {
            throw new UserAlreadyExistsException("Email is already registered");
        }

        User user = new User();
        user.setUsername(registrationDto.username());
        user.setEmail(registrationDto.email());

        String encodedPassword = passwordEncoder.encode(registrationDto.password());
        user.setPassword(encodedPassword);
        user.setRole(Role.RIDER);

        User savedUser = userRepository.save(user);
        return new UserResponseDto(
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getEmail(),
                savedUser.getRole()
        );
    }
}
