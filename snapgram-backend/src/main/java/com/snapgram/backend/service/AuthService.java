package com.snapgram.backend.service;

import com.snapgram.backend.DTO.UserRequestDto;
import com.snapgram.backend.exception.AuthenticationException;


public interface AuthService {

    String authenticateUser(UserRequestDto userRequestDto) throws AuthenticationException;
    boolean passwordMatches(String password, String encodedPassword);
}
