package com.bikertribe.harley_events.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    @JsonIgnore
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_events", joinColumns = @JoinColumn(name= "user_id"), inverseJoinColumns = @JoinColumn(name = "event_id"))
    private Set<Event> subscribedEvents = new HashSet<>();

    public void addSubscribedEvent(Event event) {
        this.subscribedEvents.add(event);
        event.getSubscribers().add(this);
    }

    public void removeSubscribedEvent(Event event){
        this.subscribedEvents.remove(event);
        event.getSubscribers().remove(this);
    }
}
