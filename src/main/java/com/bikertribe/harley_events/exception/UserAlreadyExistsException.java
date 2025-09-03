package com.bikertribe.harley_events.exception;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String username) {
        super("User already exists with username: " + username);

    }
}
