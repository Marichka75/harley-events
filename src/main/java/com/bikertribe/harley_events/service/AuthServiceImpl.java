package com.bikertribe.harley_events.service;

import com.bikertribe.harley_events.dto.LoginRequestDto;
import com.bikertribe.harley_events.dto.LoginResponseDto;
import com.bikertribe.harley_events.dto.UserRegistrationDto;
import com.bikertribe.harley_events.dto.UserResponseDto;
import com.bikertribe.harley_events.model.Role;
import com.bikertribe.harley_events.model.User;
import com.bikertribe.harley_events.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public LoginResponseDto login(LoginRequestDto request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid username"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = "JWT-TOKEN-EXAMPLE";

        return new LoginResponseDto(user.getUsername(), token);
    }

    @Override
    public UserResponseDto register(UserRegistrationDto registrationDto) {

        if (userRepository.existsByUsername(registrationDto.username())) {
            throw new RuntimeException("Username already taken");
        }
        if (userRepository.existsByEmail(registrationDto.email())) {
            throw new RuntimeException("Email already registered");
        }

        User user = new User();
        user.setUsername(registrationDto.username());
        user.setEmail(registrationDto.email());
        user.setPassword(passwordEncoder.encode(registrationDto.password()));
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
