package com.bikertribe.harley_events.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record EventCreationDto(
        @NotBlank(message = "Event name cannot be empty")
        String name,
        String description,
        @NotNull(message = "Date and time cannot be empty")
        LocalDateTime dateTime,
        @NotBlank(message = "location cannot be empty")
        String location
) {
}
