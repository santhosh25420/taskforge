package com.sk.taskforge.exception;

import com.sk.taskforge.entity.ErrorResponse;
import org.junit.jupiter.api.Test;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler exceptionHandler = new GlobalExceptionHandler();

    @Test
    void handleInvalidDtoShouldReturnBadRequestErrorResponse() {
        MockHttpServletRequest request = new MockHttpServletRequest("POST", "/api/users");
        EmptyDataException exception = new EmptyDataException("User Details are empty");
        LocalDateTime beforeHandling = LocalDateTime.now();

        ResponseEntity<ErrorResponse> response = exceptionHandler.handleInvalidUsersDto(exception, request);

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

    @Test
    void handleMethodArgumentNotValidExceptionShouldReturnFormattedFieldErrors() throws NoSuchMethodException {
        MockHttpServletRequest request = new MockHttpServletRequest("POST", "/api/users");
        BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(new Object(), "usersDto");
        bindingResult.addError(new FieldError("usersDto", "email", "must be a valid email"));
        bindingResult.addError(new FieldError("usersDto", "name", "must not be blank"));
        Method method = GlobalExceptionHandlerTest.class.getDeclaredMethod("validMethod", String.class);
        MethodArgumentNotValidException exception = new MethodArgumentNotValidException(
                new MethodParameter(method, 0), bindingResult
        );
        LocalDateTime beforeHandling = LocalDateTime.now();

        ResponseEntity<ErrorResponse> response = exceptionHandler.handleInvalidDto(exception, request);

        LocalDateTime afterHandling = LocalDateTime.now();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().status()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(response.getBody().error()).isEqualTo(HttpStatus.BAD_REQUEST.getReasonPhrase());
        assertThat(response.getBody().message()).isEqualTo("Invalid input");
        assertThat(response.getBody().path()).isEqualTo("/api/users");
        assertThat(response.getBody().details()).containsExactly(
                "email: must be a valid email",
                "name: must not be blank"
        );
        assertThat(response.getBody().timeStamp()).isBetween(beforeHandling, afterHandling);
    }

   @Test
    public void handleUserNotFoundExceptionTest_01(){
        MockHttpServletRequest request = new MockHttpServletRequest("GET","/api/users");
        UserNotFoundException exception = new UserNotFoundException("User Not found with the email");

        LocalDateTime beforeHandling = LocalDateTime.now();

        ResponseEntity<ErrorResponse> response = exceptionHandler.handleUserNotFoundException(request,exception);
       LocalDateTime afterHandling = LocalDateTime.now();
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().status()).isEqualTo(HttpStatus.CONFLICT.value());
        assertThat(response.getBody().path()).isEqualTo("/api/users");
        assertThat(response.getBody().details()).isEmpty();
        assertThat(response.getBody().timeStamp()).isBetween(beforeHandling,afterHandling);

   }

   private void validMethod(String value){

   }
}
