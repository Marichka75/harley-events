package com.bikertribe.harley_events.dto;

import com.bikertribe.harley_events.model.Role;
public record UserResponseDto(
        Long id,
        String username,
        String email,
        Role role
) {
}
