package com.snapgram.backend.exception;



public class AuthenticationException extends RuntimeException {
    public AuthenticationException(String message) {
        super(message);
    }
}