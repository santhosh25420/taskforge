package com.sk.taskforge.service;

import com.sk.taskforge.dto.OrganisationRequestDto;
import com.sk.taskforge.dto.OrganisationResponseDto;

public interface IOrganisationService {

    public OrganisationResponseDto addOrganisation(OrganisationRequestDto organisationRequestDto);

}
