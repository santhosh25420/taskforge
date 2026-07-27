package com.sk.taskforge.exception;

import com.sk.taskforge.entity.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


        @ExceptionHandler(EmptyDataException.class)
        public ResponseEntity<ErrorResponse> handleInvalidDto(EmptyDataException exception,
                                                              HttpServletRequest request){
            log.warn("Empty data recieved: {}",exception.getMessage());
            ErrorResponse errorResponse = ErrorResponse.of(HttpStatus.BAD_REQUEST,exception.getMessage(),request.getRequestURI());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
}
