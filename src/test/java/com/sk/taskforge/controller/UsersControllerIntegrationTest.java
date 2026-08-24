package com.sk.taskforge.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sk.taskforge.dto.UsersDto;
import com.sk.taskforge.dto.UserResponse;
import com.sk.taskforge.exception.EmptyDataException;
import com.sk.taskforge.service.IUsersServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UsersController.class)
class UsersControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private IUsersServices usersServices;

    @Test
    void createUserShouldAcceptJsonAndReturnServiceResult() throws Exception {
        UsersDto usersDto = UsersDto.builder()
                .name("Sahil Kumar")
                .email("sahil@example.com")
                .build();
        when(usersServices.addUsers(any(UsersDto.class))).thenReturn(true);

        mockMvc.perform(post("/taskforge/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usersDto)))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        verify(usersServices).addUsers(argThat(argument ->
                "Sahil Kumar".equals(argument.getName())
                        && "sahil@example.com".equals(argument.getEmail())));
    }

    @Test
    void createUserShouldReturnBadRequestWhenServiceRejectsUser() throws Exception {
        UsersDto usersDto = UsersDto.builder()
                .name("Sahil Kumar")
                .email("sahil@example.com")
                .build();
        when(usersServices.addUsers(any(UsersDto.class)))
                .thenThrow(new EmptyDataException("User Details are empty"));

        mockMvc.perform(post("/taskforge/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usersDto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value("Bad Request"))
                .andExpect(jsonPath("$.message").value("User Details are empty"))
                .andExpect(jsonPath("$.path").value("/taskforge/users"))
                .andExpect(jsonPath("$.details").isEmpty());
    }

    @Test
    void updateUserShouldReturnTheUpdatedResource() throws Exception {
        String id = "3f8b95f0-b9d3-4eb9-b5c8-2572148ec9f1";
        UsersDto request = UsersDto.builder().name("Updated Name").email("updated@example.com").build();
        when(usersServices.updateUser(eq(java.util.UUID.fromString(id)), any(UsersDto.class)))
                .thenReturn(new UserResponse(java.util.UUID.fromString(id), request.getName(), request.getEmail(), null, null));

        mockMvc.perform(put("/taskforge/users/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value("Updated Name"))
                .andExpect(jsonPath("$.email").value("updated@example.com"));
    }
}
