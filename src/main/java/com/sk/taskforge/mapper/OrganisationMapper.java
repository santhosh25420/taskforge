package com.sk.taskforge.mapper;

import com.sk.taskforge.dto.OrganisationRequestDto;
import com.sk.taskforge.dto.OrganisationResponseDto;
import com.sk.taskforge.entity.Organisation;
import com.sk.taskforge.entity.Users;
import com.sk.taskforge.exception.EmptyDataException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
public class OrganisationMapper {

    public  Organisation toEntity(OrganisationRequestDto organisationRequestDto, Users owner){


        if(Objects.isNull(organisationRequestDto)){
            log.warn("Organisation Request data is empty: {}",organisationRequestDto);
            throw new EmptyDataException("organisation details cannot be null");
        }

        if(Objects.isNull(owner)){
            log.warn("Owner data is empty: {}",owner);
            throw new EmptyDataException("owner details cannot be null");

        }
        Organisation organisation = Organisation.builder()
                .name(organisationRequestDto.getName())
                .owner(owner)
                .build();
        log.info("Created Organisation entity: {}",organisation);
        return organisation;

    }

    public  OrganisationResponseDto toResponse(Organisation organisation){
        if(Objects.isNull(organisation)){
            log.warn("Organisation data is empty: {}",organisation);
            throw new EmptyDataException("organisation details cannot be null");
        }
        return OrganisationResponseDto.builder()
                .name(organisation.getName())
                .owner(organisation.getOwner().getName())
                .build();
    }
}
