package com.snapgram.backend.exception;

import com.snapgram.backend.service.UserService;
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


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    Map<String, String> errorResponse = new HashMap<>();

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorDetails> handleUserAlreadyExistsException(UserAlreadyExistsException ex, WebRequest request){
        ErrorDetails error=new ErrorDetails(ex.getMessage(),request.getDescription(false),  LocalDateTime.now());
        return new ResponseEntity <ErrorDetails>(error,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ErrorDetails> handleUserAlreadyExistsException(UserException ex, WebRequest request){
        ErrorDetails error=new ErrorDetails(ex.getMessage(),request.getDescription(false),  LocalDateTime.now());
        return new ResponseEntity <ErrorDetails>(error,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorDetails> handleConstraintViolationException(ConstraintViolationException ex,  WebRequest request){

        StringBuilder errorMessage = new StringBuilder();

        // Collect all constraint violation messages
        ex.getConstraintViolations().forEach(violation ->
                errorMessage.append(violation.getMessage()).append("; ")
        );

        ErrorDetails errorDetails = new ErrorDetails(
                errorMessage.toString(),
                request.getDescription(false),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }



    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleUserNotFoundException(UserNotFoundException ex, WebRequest request){
        ErrorDetails error=new ErrorDetails(ex.getMessage(),request.getDescription(false), LocalDateTime.now());
        return new ResponseEntity <ErrorDetails>(error,HttpStatus.NOT_FOUND);

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

    @ExceptionHandler(InvalidCredentialException.class)
    public ResponseEntity<ErrorDetails> handleTokenExpiredException(InvalidCredentialException ex, WebRequest request) {
        ErrorDetails error=new ErrorDetails(ex.getMessage(),request.getDescription(false), LocalDateTime.now());
        return new ResponseEntity <ErrorDetails>(error,HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<ErrorDetails> handleTokenExpiredException(TokenExpiredException ex, WebRequest request) {
        ErrorDetails error=new ErrorDetails(ex.getMessage(),request.getDescription(false), LocalDateTime.now());
        return new ResponseEntity <ErrorDetails>(error,HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ErrorDetails> handleJAuthenticationException(AuthenticationException ex, WebRequest request) {
        ErrorDetails error=new ErrorDetails(ex.getMessage(),request.getDescription(false), LocalDateTime.now());
        return new ResponseEntity <ErrorDetails>(error,HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleJAuthenticationException(Exception ex, WebRequest request) {
        ErrorDetails error=new ErrorDetails(ex.getMessage(),request.getDescription(false), LocalDateTime.now());
        return new ResponseEntity <ErrorDetails>(error,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PostException.class)
    public ResponseEntity<ErrorDetails> handlePostException(PostException ex, WebRequest request) {
        ErrorDetails error=new ErrorDetails(ex.getMessage(),request.getDescription(false), LocalDateTime.now());
        return new ResponseEntity <ErrorDetails>(error,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CommentException.class)
    public ResponseEntity<ErrorDetails> handleCommentException(CommentException ex, WebRequest request) {
        ErrorDetails error=new ErrorDetails(ex.getMessage(),request.getDescription(false), LocalDateTime.now());
        return new ResponseEntity <ErrorDetails>(error,HttpStatus.BAD_REQUEST);
    }

}
