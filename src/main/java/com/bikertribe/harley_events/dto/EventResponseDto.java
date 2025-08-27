package com.bikertribe.harley_events.dto;

import java.time.LocalDateTime;
import java.util.Set;

public record EventResponseDto(
        Long id,
        String name,
        String description,
        LocalDateTime dateTime,
        String location,
        Set<UserResponseDto> subscribers
        ) {
}
