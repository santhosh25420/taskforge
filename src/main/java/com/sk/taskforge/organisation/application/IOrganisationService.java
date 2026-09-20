package com.sk.taskforge.organisation.application;

import com.sk.taskforge.organisation.dto.OrganisationRequestDto;
import com.sk.taskforge.organisation.dto.OrganisationResponseDto;

public interface IOrganisationService {

    OrganisationResponseDto addOrganisation(OrganisationRequestDto organisationRequestDto);

}
