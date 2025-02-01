package com.snapgram.backend.exception;

import com.snapgram.backend.service.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@ControllerAdvice
public class GlobalExceptionHandlier {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleUserAlreadyExistsException(UserAlreadyExistsException ex){
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message", ex.getMessage());
        return new ResponseEntity <>(errorResponse,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> handleConstraintViolationException(ConstraintViolationException ex){
        Map<String, String> errorResponse = new HashMap<>();

        Set <ConstraintViolation <?>> violations=ex.getConstraintViolations();
        violations.forEach(violation->{
            String message=violation.getMessage();
            errorResponse.put("message",message);
        });
        return new ResponseEntity <>(errorResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleUserNotFoundException(UserNotFoundException ex){
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message", ex.getMessage());
        return new ResponseEntity <>(errorResponse,HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map <String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        // if there are multiple field exceptions then to handle those with structured response format we use this
        // approach
        Map <String, String> errors = new HashMap <>();
                ex.getBindingResult().getAllErrors().
                forEach(error -> {
                    String fieldName = ((FieldError) error).getField();
                    String errorMessage = error.getDefaultMessage();
                    logger.warn(errorMessage);
                    errors.put(fieldName,errorMessage);
                });

        return new ResponseEntity <>(errors,HttpStatus.BAD_REQUEST);
    }
    // Handle expired JWT token
    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<Object> handleExpiredJwtException(ExpiredJwtException ex, WebRequest request) {
        // Return a response with FORBIDDEN status and a message
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body("Token expired: " + ex.getMessage());
    }
    // Handle invalid signature or other JWT signature errors
    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<Object> handleSignatureException(SignatureException ex, WebRequest request) {
        // Return a response with FORBIDDEN status and a message
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body("Invalid token signature: " + ex.getMessage());
    }
    // Handle other general JWT related errors
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleJwtException(Exception ex, WebRequest request) {
        // Handle any other exceptions that may arise
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body("Authentication failed: " + ex.getMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<Object> handleJAuthenticationException(AuthenticationException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        System.out.println(ex.getMessage());
        errorResponse.put("message", ex.getMessage());
        logger.warn(errorResponse.get("message"));
        return new ResponseEntity <>(errorResponse,HttpStatus.UNAUTHORIZED);
    }

}
