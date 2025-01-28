package com.snapgram.backend.exception;

import com.snapgram.backend.service.UserService;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@ControllerAdvice
public class GlobalExceptionHandlier {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @ExceptionHandler({UserAlreadyExistsException.class, ConstraintViolationException.class})
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
            String propertyName= violation.getPropertyPath().toString();
            String message=violation.getMessage();
            errorResponse.put(propertyName,message);
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
}
