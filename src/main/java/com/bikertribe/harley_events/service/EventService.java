package com.bikertribe.harley_events.service;

import com.bikertribe.harley_events.dto.EventCreationDto;
import com.bikertribe.harley_events.dto.EventResponseDto;
import com.bikertribe.harley_events.dto.EventUpdateDto;
import com.bikertribe.harley_events.exception.EventNotFoundException;
import com.bikertribe.harley_events.exception.UserNotFoundException;
import com.bikertribe.harley_events.dto.UserResponseDto;
import com.bikertribe.harley_events.model.User;
import com.bikertribe.harley_events.model.Event;
import com.bikertribe.harley_events.repository.EventRepository;
import com.bikertribe.harley_events.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    public EventService(EventRepository eventRepository, UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    public EventResponseDto createEvent(EventCreationDto eventDto) {
        Event event = new Event();
        event.setName(eventDto.name());
        event.setDescription(eventDto.description());
        event.setDateTime(eventDto.dateTime());
        event.setLocation(eventDto.location());

        Event savedEvent = eventRepository.save(event);
        return mapToResponseDto(savedEvent);
    }

    public List<EventResponseDto> getAllEvents() {
        return  eventRepository.findAll().stream().map(this::mapToResponseDto).collect(Collectors.toList());
    }

    public EventResponseDto getEventById(Long id) {
        Event event = eventRepository.findById(id).orElseThrow(() -> new EventNotFoundException("Event not found with ID: " + id));
        return  mapToResponseDto(event);
    }

    public EventResponseDto updateEvent(long id, EventUpdateDto eventDto) {
        Event existingEvent = eventRepository.findById(id).orElseThrow(() -> new EventNotFoundException("Event not found with ID: " + id));

        existingEvent.setName(eventDto.name());
        existingEvent.setDescription(eventDto.description());
        existingEvent.setDateTime(eventDto.dateTime());
        existingEvent.setLocation(eventDto.location());

        Event updatedEvent = eventRepository.save(existingEvent);
        return mapToResponseDto(updatedEvent);
    }

    public void deleteEvent(Long id) {
        if (!eventRepository.existsById(id)) {
            throw new EventNotFoundException("Event not found with ID: " + id);
        }
        eventRepository.deleteById(id);
    }

    public EventResponseDto subscribeUserToEvent(long eventId, Long userId) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new EventNotFoundException("Event not found with ID: " + eventId));
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));

        user.addSubscribedEvent(event);
        userRepository.save(user);

        return mapToResponseDto(event);
    }

    public EventResponseDto unsubscribeUserFromEvent(Long eventId, long userId) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new EventNotFoundException("Event not found with ID: " + eventId));
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));

        user.removeSubscribedEvent(event);
        userRepository.save(user);

        return mapToResponseDto(event);
    }

   private EventResponseDto mapToResponseDto(Event event) {
       return new EventResponseDto(
               event.getId(),
               event.getName(),
               event.getDescription(),
               event.getDateTime(),
               event.getLocation(),
               event.getSubscribers().stream().map(user -> new UserResponseDto(
                       user.getId(),
                       user.getUsername(),
                       user.getEmail(),
                       user.getRole()
               )).collect(Collectors.toSet()
               ));
   }
}

