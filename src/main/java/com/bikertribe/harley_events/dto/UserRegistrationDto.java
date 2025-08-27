package com.bikertribe.harley_events.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UserRegistrationDto(
        @NotBlank(message = "Username cannot be empty")
        String username,
        @Email(message = "Email should be valid")
        @NotBlank(message = "Email cannot be empty")
        String email,
        @NotBlank(message = "Password cannot be empty")
        @Size(min = 8, message = "Password must be at least 8 characters long")
        String password
) {
}
