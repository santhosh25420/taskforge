package com.sk.taskforge.organisation.application;


import com.sk.taskforge.common.exception.EmptyDataException;
import com.sk.taskforge.organisation.domain.Organisation;
import com.sk.taskforge.organisation.domain.OrganisationMembers;
import com.sk.taskforge.organisation.dto.OrganisationRequestDto;
import com.sk.taskforge.organisation.dto.OrganisationResponseDto;
import com.sk.taskforge.organisation.exception.InvalidOrgNameException;
import com.sk.taskforge.organisation.mapper.OrganisationMapper;
import com.sk.taskforge.organisation.persistence.IOrganisationMemberRepository;
import com.sk.taskforge.organisation.persistence.IOrganisationRepository;
import com.sk.taskforge.user.application.IUsersServices;
import com.sk.taskforge.user.domain.Users;
import com.sk.taskforge.user.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service
public class ImpOrganisationService implements IOrganisationService{

    private IOrganisationRepository orgRepo;
    private IOrganisationMemberRepository organisationMemberRepository;
    private OrganisationMapper mapper;
    private IUsersServices usersServices;

    public ImpOrganisationService(IOrganisationRepository repository,
                                  IOrganisationMemberRepository organisationMemberRepository,
                                  OrganisationMapper mapper,
                                  IUsersServices usersServices){
        this.orgRepo = repository;
        this.organisationMemberRepository = organisationMemberRepository;
        this.mapper = mapper;
        this.usersServices = usersServices;
    }

    @Override
    @Transactional
    public OrganisationResponseDto addOrganisation(OrganisationRequestDto organisationRequestDto) {
        if(Objects.isNull(organisationRequestDto)){
            log.warn("Organisation Request Data is empty");
            throw new EmptyDataException("Organisation Details cannot be null pr empty");
        }
        Users ownerData;
        try{
            ownerData = usersServices.getUsersEntityById(organisationRequestDto.getOwnerId());
            Organisation savedOrganisation = orgRepo.save(mapper.toEntity(organisationRequestDto, ownerData));

            OrganisationMembers ownerMember = OrganisationMembers.builder()
                    .organisation(savedOrganisation)
                    .user(ownerData)
                    .name(ownerData.getName())
                    .role("OWNER")
                    .build();
            organisationMemberRepository.save(ownerMember);

            log.info("Saved organisation details: {}",savedOrganisation);
            return mapper.toResponse(savedOrganisation);
        }catch(UserNotFoundException ex){
            log.warn("Owner doesn't exist with id: "+organisationRequestDto.getOwnerId());
            throw new UserNotFoundException("Owner doesn't exist with id: "+organisationRequestDto.getOwnerId());
        }
        catch(DataIntegrityViolationException ex){
            log.warn("Organisation with name : {} already exists", organisationRequestDto.getName());
            throw new InvalidOrgNameException("Organisation with name : " + organisationRequestDto.getName() + " already exists");
        }
    }
}
