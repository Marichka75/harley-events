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

    public  EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    @PreAuthorize("hasRole('RIDER') or hasRole('ADMIN')")
    public ResponseEntity<List<EventResponseDto>> getAllEvents() {
        List<EventResponseDto> events = eventService.getAllEvents();
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('RIDER') or hasRole('ADMIN')")
    public ResponseEntity<EventResponseDto> getEventById(@PathVariable Long id) {
        EventResponseDto event = eventService.getEventById(id);
        return new ResponseEntity<>(event, HttpStatus.OK);
}

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EventResponseDto> createEvent(@RequestBody EventCreationDto eventDto) {
        EventResponseDto createdEvent = eventService.createEvent(eventDto);
        return  new ResponseEntity<>(createdEvent, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EventResponseDto> updateEvent(@PathVariable Long id, @RequestBody EventUpdateDto eventDto) {
        EventResponseDto updatedEvent =eventService.updateEvent(id, eventDto);
        return new ResponseEntity<>(updatedEvent, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteEvent(@PathVariable long id) {
        eventService.deleteEvent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{eventId}/subscribe/{userId}")
    @PreAuthorize("hasRole('RIDER')")
    public ResponseEntity<EventResponseDto> subscribeUserToEvent(@PathVariable Long eventId, @PathVariable Long userId) {
        EventResponseDto updatedEvent = eventService.subscribeUserToEvent(eventId, userId);
        return new ResponseEntity<>(updatedEvent, HttpStatus.OK);
    }

    @PutMapping("/{eventId}/unsubscribe/{userId}")
    @PreAuthorize("hasRole('RIDER')")
    public ResponseEntity<EventResponseDto> unsubscribeUserFromEvent(@PathVariable long eventId, @PathVariable Long userId) {
        EventResponseDto updatedEvent = eventService.unsubscribeUserFromEvent(eventId, userId);
        return new ResponseEntity<>(updatedEvent, HttpStatus.OK);
    }
}
