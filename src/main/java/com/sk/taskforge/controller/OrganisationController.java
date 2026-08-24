package com.sk.taskforge.controller;

import com.sk.taskforge.dto.OrganisationRequestDto;
import com.sk.taskforge.dto.OrganisationResponseDto;
import com.sk.taskforge.service.IOrganisationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/organisation")
public class OrganisationController {

    private IOrganisationService service;

    public OrganisationController(IOrganisationService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<OrganisationResponseDto> newOrganisation(@RequestBody @Valid OrganisationRequestDto organisationRequest){
        OrganisationResponseDto responseDto = service.addOrganisation(organisationRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .body(responseDto);
    }
}
