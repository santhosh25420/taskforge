package com.sk.taskforge.service;

import com.sk.taskforge.dto.OrganisationRequestDto;
import com.sk.taskforge.dto.OrganisationResponseDto;

public interface IOrganisationService {

    OrganisationResponseDto addOrganisation(OrganisationRequestDto organisationRequestDto);

}
