package com.snapgram.backend.service;

import com.snapgram.backend.exception.AuthenticationException;


public interface AuthService {

    String authenticateUser(String username, String password) throws AuthenticationException;
    boolean passwordMatches(String password, String encodedPassword);
}
