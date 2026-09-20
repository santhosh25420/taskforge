package com.sk.taskforge.organisation.dto;

import lombok.Builder;

@Builder
public record OrganisationResponseDto(String name,String owner) {

}
