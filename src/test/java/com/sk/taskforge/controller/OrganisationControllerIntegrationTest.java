package com.sk.taskforge.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sk.taskforge.dto.OrganisationRequestDto;
import com.sk.taskforge.dto.OrganisationResponseDto;
import com.sk.taskforge.exception.InvalidOrgNameException;
import com.sk.taskforge.exception.UserNotFoundException;
import com.sk.taskforge.service.IOrganisationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrganisationController.class)
class OrganisationControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private IOrganisationService organisationService;

    @Test
    void createOrganisationShouldReturnCreatedOrganisation() throws Exception {
        UUID ownerId = UUID.randomUUID();
        OrganisationRequestDto request = OrganisationRequestDto.builder()
                .name("TaskForge").ownerId(ownerId).build();
        when(organisationService.addOrganisation(any(OrganisationRequestDto.class)))
                .thenReturn(OrganisationResponseDto.builder().name("TaskForge").owner("Sahil Kumar").build());

        mockMvc.perform(post("/taskforge/organisation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("TaskForge"))
                .andExpect(jsonPath("$.owner").value("Sahil Kumar"));
    }

    @Test
    void createOrganisationShouldRejectNullOwner() throws Exception {
        String request = "{\"name\":\"TaskForge\",\"ownerId\":null}";

        mockMvc.perform(post("/taskforge/organisation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Invalid input"))
                .andExpect(jsonPath("$.details[0]").value("ownerId: Organisation owner can't be null"));
    }

    @Test
    void createOrganisationShouldReturnBadRequestForDuplicateName() throws Exception {
        UUID ownerId = UUID.randomUUID();
        OrganisationRequestDto request = OrganisationRequestDto.builder()
                .name("TaskForge").ownerId(ownerId).build();
        when(organisationService.addOrganisation(any(OrganisationRequestDto.class)))
                .thenThrow(new InvalidOrgNameException("Organisation with name : TaskForge already exists"));

        mockMvc.perform(post("/taskforge/organisation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Organisation with name : TaskForge already exists"))
                .andExpect(jsonPath("$.path").value("/taskforge/organisation"));
    }

    @Test
    void createOrganisationShouldReturnNotFoundForMissingOwner() throws Exception {
        UUID ownerId = UUID.randomUUID();
        OrganisationRequestDto request = OrganisationRequestDto.builder()
                .name("TaskForge").ownerId(ownerId).build();
        when(organisationService.addOrganisation(any(OrganisationRequestDto.class)))
                .thenThrow(new UserNotFoundException("Owner doesn't exist with id: " + ownerId));

        mockMvc.perform(post("/taskforge/organisation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Owner doesn't exist with id: " + ownerId));
    }
}
