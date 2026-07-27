package com.sk.taskforge.controller;

import com.sk.taskforge.dto.UsersDto;
import com.sk.taskforge.service.IUsersServices;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
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
}
