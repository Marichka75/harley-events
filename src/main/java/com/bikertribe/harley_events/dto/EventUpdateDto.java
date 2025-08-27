package com.bikertribe.harley_events.dto;

import java.time.LocalDateTime;

public record EventUpdateDto(String name, String description, LocalDateTime dateTime,String location) {
}
