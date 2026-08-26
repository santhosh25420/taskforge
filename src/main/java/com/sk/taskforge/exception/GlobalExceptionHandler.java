package com.sk.taskforge.exception;

import com.sk.taskforge.entity.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.dao.DataIntegrityViolationException;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ErrorResponse> handleInvalidDto(MethodArgumentNotValidException exception,
                                                              HttpServletRequest request){
            log.warn("Invalid data received: {}",exception.getMessage());
            List<String> errors = exception.getBindingResult()
                    .getFieldErrors()
                    .stream()
                    .map(fieldError -> fieldError.getField()+": "+fieldError.getDefaultMessage())
                    .toList();

            ErrorResponse errorResponse = ErrorResponse.of(
                    HttpStatus.BAD_REQUEST,
                    "Invalid input",
                    request.getRequestURI(),
                    errors
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }

        @ExceptionHandler(EmptyDataException.class)
        public ResponseEntity<ErrorResponse> handleInvalidUsersDto(EmptyDataException exception,
                                                              HttpServletRequest request){
            log.warn("Empty data recieved: {}",exception.getMessage());
            ErrorResponse errorResponse = ErrorResponse.of(HttpStatus.BAD_REQUEST,exception.getMessage(),request.getRequestURI());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }

        @ExceptionHandler(UserNotFoundException.class)
        public ResponseEntity<ErrorResponse> handleUserNotFoundException(HttpServletRequest request,
                                                                     UserNotFoundException ex){
            log.warn(ex.getMessage());
            ErrorResponse errorResponse = ErrorResponse.of(
                    HttpStatus.NOT_FOUND,
                    ex.getMessage(),
                    request.getRequestURI()
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }

        @ExceptionHandler({UserAlreadyExistsException.class, DataIntegrityViolationException.class})
        public ResponseEntity<ErrorResponse> handleConflict(RuntimeException ex, HttpServletRequest request) {
            log.warn("User update conflict: {}", ex.getMessage());
            ErrorResponse errorResponse = ErrorResponse.of(
                    HttpStatus.CONFLICT,
                    ex instanceof UserAlreadyExistsException ? ex.getMessage() : "Email is already in use",
                    request.getRequestURI()
            );
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        }

        @ExceptionHandler(InvalidOrgNameException.class)
        public ResponseEntity<ErrorResponse> handleInvalidOrgNameException(InvalidOrgNameException ex, HttpServletRequest request) {
            log.warn("Invalid organisation name: {}", ex.getMessage());
            ErrorResponse errorResponse = ErrorResponse.of(
                    HttpStatus.BAD_REQUEST,
                    ex.getMessage(),
                    request.getRequestURI()
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }

}