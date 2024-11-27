package com.snapgram.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandlier {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<String> handleUserAlreadyExistsException(UserAlreadyExistsException ex){
        return new ResponseEntity <>(ex.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex){
        return new ResponseEntity <>(ex.getMessage(),HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        // if there are multiple field exceptions then to handle those with structured response format we use this
        // approach
        List <String> errors=ex.getBindingResult().getAllErrors()
        .stream().map(error->((FieldError) error).getField() + ":"+ error.getDefaultMessage())
                        .toList();
        return new ResponseEntity <>(ex.getMessage(),HttpStatus.BAD_REQUEST);
    }

}
