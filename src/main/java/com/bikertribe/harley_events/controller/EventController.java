package com.bikertribe.harley_events.controller;

import com.bikertribe.harley_events.dto.EventCreationDto;
import com.bikertribe.harley_events.dto.EventResponseDto;
import com.bikertribe.harley_events.dto.EventUpdateDto;
import com.bikertribe.harley_events.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    @PreAuthorize("hasRole('RIDER') or hasRole('ADMIN')")
    public ResponseEntity<List<EventResponseDto>> getAllEvents() {
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('RIDER') or hasRole('ADMIN')")
    public ResponseEntity<EventResponseDto> getEventById(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.getEventById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EventResponseDto> createEvent(@RequestBody EventCreationDto eventDto) {
        return new ResponseEntity<>(eventService.createEvent(eventDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EventResponseDto> updateEvent(@PathVariable Long id, @RequestBody EventUpdateDto eventDto) {
        return ResponseEntity.ok(eventService.updateEvent(id, eventDto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteEvent(@PathVariable long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.ok("Event removed successfully");
    }

    @PutMapping("/{eventId}/subscribe/{userId}")
    @PreAuthorize("hasRole('RIDER')")
    public ResponseEntity<EventResponseDto> subscribeUserToEvent(@PathVariable Long eventId, @PathVariable Long userId) {
        return ResponseEntity.ok(eventService.subscribeUserToEvent(eventId, userId));
    }

    @PutMapping("/{eventId}/unsubscribe/{userId}")
    @PreAuthorize("hasRole('RIDER')")
    public ResponseEntity<EventResponseDto> unsubscribeUserFromEvent(@PathVariable long eventId, @PathVariable Long userId) {
        return ResponseEntity.ok(eventService.unsubscribeUserFromEvent(eventId, userId));
    }
}
