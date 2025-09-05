package com.bikertribe.harley_events.service;

import com.bikertribe.harley_events.dto.LoginRequestDto;
import com.bikertribe.harley_events.dto.LoginResponseDto;
import com.bikertribe.harley_events.dto.UserRegistrationDto;
import com.bikertribe.harley_events.dto.UserResponseDto;

public interface AuthService {
    LoginResponseDto login(LoginRequestDto loginDto);
    UserResponseDto register(UserRegistrationDto registrationDto);
}
