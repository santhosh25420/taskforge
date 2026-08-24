package com.sk.taskforge.service;


import com.sk.taskforge.dto.OrganisationRequestDto;
import com.sk.taskforge.dto.OrganisationResponseDto;
import com.sk.taskforge.dto.UsersDto;
import com.sk.taskforge.entity.Organisation;
import com.sk.taskforge.entity.Users;
import com.sk.taskforge.exception.EmptyDataException;
import com.sk.taskforge.exception.UserNotFoundException;
import com.sk.taskforge.mapper.OrganisationMapper;
import com.sk.taskforge.repos.IOrganisationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service
public class ImpOrganisationService implements IOrganisationService{

    private IOrganisationRepository orgRepo;
    private OrganisationMapper mapper;
    private IUsersServices usersServices;

    public ImpOrganisationService(IOrganisationRepository repository, OrganisationMapper mapper,
                                  IUsersServices usersServices){
        this.orgRepo = repository;
        this.mapper = mapper;
        this.usersServices = usersServices;
    }

    @Override
    public OrganisationResponseDto addOrganisation(OrganisationRequestDto organisationRequestDto) {
        if(Objects.isNull(organisationRequestDto)){
            log.warn("Organisation Request Data is empty");
            throw new EmptyDataException("Organisation Details cannot be null pr empty");
        }
        Users ownerData;
        try{
            ownerData = usersServices.getUsersEntityById(organisationRequestDto.getOwnerId());
        }catch(UserNotFoundException ex){
            log.warn("Owner doesn't exist with id: "+organisationRequestDto.getOwnerId());
            throw new UserNotFoundException("Owner doesn't exist with id: "+organisationRequestDto.getOwnerId());
        }

        Organisation savedOrganisation = orgRepo.save(mapper.toEntity(organisationRequestDto, ownerData));
        log.info("Saved organisation details: {}",savedOrganisation);
        return mapper.toResponse(savedOrganisation);

    }
}
