package com.sk.taskforge.controller;

import com.sk.taskforge.dto.UsersDto;
import com.sk.taskforge.exception.EmptyDataException;
import com.sk.taskforge.exception.UserNotFoundException;
import com.sk.taskforge.service.IUsersServices;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsersControllerTest {

    @Mock
    private IUsersServices usersServices;

    @InjectMocks
    private UsersController usersController;

    @Test
    void createUserShouldReturnOkWithServiceResult() {
        UsersDto usersDto = UsersDto.builder()
                .name("Sahil Kumar")
                .email("sahil@example.com")
                .build();
        when(usersServices.addUsers(usersDto)).thenReturn(true);

        ResponseEntity<Boolean> response = usersController.createUser(usersDto);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isTrue();
        verify(usersServices).addUsers(usersDto);
    }

    @Test
    void createUserShouldPropagateEmptyDataException() {
        UsersDto usersDto = UsersDto.builder()
                .name("Sahil Kumar")
                .email("sahil@example.com")
                .build();
        when(usersServices.addUsers(usersDto))
                .thenThrow(new EmptyDataException("User Details are empty"));

        assertThatThrownBy(() -> usersController.createUser(usersDto))
                .isInstanceOf(EmptyDataException.class)
                .hasMessage("User Details are empty");

        verify(usersServices).addUsers(usersDto);
    }

    @Test
    public void getUserByEmailShouldReturnOk_01(){
        UsersDto usersDto = UsersDto.builder()
                .name("Sahil Kumar")
                .email("sahil@example.com")
                .build();

        when(usersServices.getUserByEmail(anyString())).thenReturn(usersDto);

        ResponseEntity<UsersDto> response = usersController.getByEmail("sahil@example.com");

        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).isEqualTo(usersDto);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(usersServices).getUserByEmail("sahil@example.com");
    }

    @Test
    void getUserByEmailShouldPropagateUserNotFoundException() {
        String email = "missing@example.com";
        when(usersServices.getUserByEmail(email))
                .thenThrow(new UserNotFoundException("User not found with mail: " + email));

        assertThatThrownBy(() -> usersController.getByEmail(email))
                .isInstanceOf(UserNotFoundException.class)
                .hasMessage("User not found with mail: " + email);

        verify(usersServices).getUserByEmail(email);
    }

    @Test
    void getUserByIdShouldReturnNotFoundForInvalidUuid() {
        ResponseEntity<UsersDto> response = usersController.getById("not-a-uuid");

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isNull();
    }

    @Test
    void getUserByIdShouldReturnNotFoundWhenUserDoesNotExist() {
        UUID id = UUID.randomUUID();
        when(usersServices.getUserById(id))
                .thenThrow(new UserNotFoundException("User not found with id: " + id));

        ResponseEntity<UsersDto> response = usersController.getById(id.toString());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isNull();
        verify(usersServices).getUserById(id);
    }
    
}
