package com.sk.taskforge.entity;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorResponse(LocalDateTime timeStamp,
                            int status,
                            String error,
                            String message,
                            String path,
                            List<String> details) {

    public static  ErrorResponse of(HttpStatus status,String message, String path){
        return new ErrorResponse(LocalDateTime.now(), status.value(), status.getReasonPhrase(), message, path, List.of());
    }

    public static  ErrorResponse of( HttpStatus status,String message, String path,List<String> details){
        return new ErrorResponse(LocalDateTime.now(), status.value(), status.getReasonPhrase(), message, path, details);
    }
}
