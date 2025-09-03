package com.bikertribe.harley_events.service;

import com.bikertribe.harley_events.dto.UserRegistrationDto;
import com.bikertribe.harley_events.dto.UserResponseDto;
import com.bikertribe.harley_events.dto.UserUpdateDto;
import com.bikertribe.harley_events.exception.UserNotFoundException;
import com.bikertribe.harley_events.model.Role;
import com.bikertribe.harley_events.model.User;
import com.bikertribe.harley_events.exception.UserAlreadyExistsException;
import com.bikertribe.harley_events.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        user.setPassword(passwordEncoder.encode(registrationDto.password()));
        user.setRole(Role.RIDER);

        User savedUser = userRepository.save(user);
        return mapToResponseDto(savedUser);
    }
    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll().stream().map(this::mapToResponseDto).collect(Collectors.toList());
    }

    public UserResponseDto getUserById(Long id) {
        User user =userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        return mapToResponseDto(user);
    }

    public UserResponseDto updateUser(Long id, UserUpdateDto userDto) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        if (userDto.username() != null && !userDto.username().equals(existingUser.getUsername())) {
            if(userRepository.existsByUsername(userDto.username())) {
                throw new UserAlreadyExistsException("User name is already taken");}
                existingUser.setUsername(userDto.username());
            }
            if (userDto.email() != null && !userDto.email().equals(existingUser.getEmail())) {
              if (userRepository.existsByEmail((userDto.email()))) {
                  throw new UserAlreadyExistsException("Email is already registered");
              }
              existingUser.setEmail(userDto.email());
            }
            if (userDto.password() !=null && !userDto.password().isBlank()) {
                existingUser.setPassword(passwordEncoder.encode(userDto.password()));
            }

            User updatedUser = userRepository.save(existingUser);
            return mapToResponseDto(updatedUser);
        }
        public void deleteUser(Long id) {
            if (!userRepository.existsById(id)) {
                throw new UserNotFoundException(id);
            }
            userRepository.deleteById(id);
        }

        private UserResponseDto mapToResponseDto(User user) {
            return new UserResponseDto(
                    user.getId(),
                    user.getUsername(),
                    user.getEmail(),
                    user.getRole()
            );
        }
    }

