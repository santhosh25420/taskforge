package com.sk.taskforge.exception;

import com.sk.taskforge.entity.ErrorResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler exceptionHandler = new GlobalExceptionHandler();

    @Test
    void handleInvalidDtoShouldReturnBadRequestErrorResponse() {
        MockHttpServletRequest request = new MockHttpServletRequest("POST", "/api/users");
        EmptyDataException exception = new EmptyDataException("User Details are empty");
        LocalDateTime beforeHandling = LocalDateTime.now();

        ResponseEntity<ErrorResponse> response = exceptionHandler.handleInvalidDto(exception, request);

        LocalDateTime afterHandling = LocalDateTime.now();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().status()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(response.getBody().error()).isEqualTo(HttpStatus.BAD_REQUEST.getReasonPhrase());
        assertThat(response.getBody().message()).isEqualTo("User Details are empty");
        assertThat(response.getBody().path()).isEqualTo("/api/users");
        assertThat(response.getBody().details()).isEmpty();
        assertThat(response.getBody().timeStamp()).isBetween(beforeHandling, afterHandling);
    }
}
