package com.bikertribe.harley_events.exception;

public class UsernameAlreadyExistsException extends RuntimeException {
    public UsernameAlreadyExistsException(String username) {
        super("A user with username " + username + "already exists");
    }
}
