package com.bikertribe.harley_events.dto;

public class LoginResponseDto {

    private String username;
    private String token;

    public LoginResponseDto() {
    }

    public LoginResponseDto(String username, String token) {
        this.username = username;
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
