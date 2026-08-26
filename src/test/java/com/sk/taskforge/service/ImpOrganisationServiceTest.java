package com.sk.taskforge.service;

import com.sk.taskforge.dto.OrganisationRequestDto;
import com.sk.taskforge.dto.OrganisationResponseDto;
import com.sk.taskforge.entity.Organisation;
import com.sk.taskforge.entity.OrganisationMembers;
import com.sk.taskforge.entity.Users;
import com.sk.taskforge.exception.EmptyDataException;
import com.sk.taskforge.exception.InvalidOrgNameException;
import com.sk.taskforge.exception.UserNotFoundException;
import com.sk.taskforge.mapper.OrganisationMapper;
import com.sk.taskforge.repos.IOrganisationMemberRepository;
import com.sk.taskforge.repos.IOrganisationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ImpOrganisationServiceTest {

    @Mock
    private IOrganisationRepository organisationRepository;

    @Mock
    private IOrganisationMemberRepository organisationMemberRepository;

    @Mock
    private OrganisationMapper organisationMapper;

    @Mock
    private IUsersServices usersServices;

    @InjectMocks
    private ImpOrganisationService organisationService;

    @Test
    void addOrganisationShouldRejectNullRequest() {
        assertThatThrownBy(() -> organisationService.addOrganisation(null))
                .isInstanceOf(EmptyDataException.class)
                .hasMessage("Organisation Details cannot be null pr empty");

        verifyNoInteractions(organisationRepository, organisationMemberRepository,
                organisationMapper, usersServices);
    }

    @Test
    void addOrganisationShouldPersistOrganisationAndOwnerMembership() {
        UUID ownerId = UUID.randomUUID();
        Users owner = Users.builder().id(ownerId).name("Sahil Kumar").build();
        OrganisationRequestDto request = OrganisationRequestDto.builder()
                .name("TaskForge").ownerId(ownerId).build();
        Organisation organisation = Organisation.builder().name("TaskForge").owner(owner).build();
        OrganisationResponseDto expected = OrganisationResponseDto.builder()
                .name("TaskForge").owner("Sahil Kumar").build();

        when(usersServices.getUsersEntityById(ownerId)).thenReturn(owner);
        when(organisationMapper.toEntity(request, owner)).thenReturn(organisation);
        when(organisationRepository.save(organisation)).thenReturn(organisation);
        when(organisationMapper.toResponse(organisation)).thenReturn(expected);

        OrganisationResponseDto result = organisationService.addOrganisation(request);

        assertThat(result).isEqualTo(expected);
        verify(organisationRepository).save(organisation);
        ArgumentCaptor<OrganisationMembers> memberCaptor = ArgumentCaptor.forClass(OrganisationMembers.class);
        verify(organisationMemberRepository).save(memberCaptor.capture());
        OrganisationMembers ownerMember = memberCaptor.getValue();
        assertThat(ownerMember.getOrganisation()).isSameAs(organisation);
        assertThat(ownerMember.getUser()).isSameAs(owner);
        assertThat(ownerMember.getName()).isEqualTo("Sahil Kumar");
        assertThat(ownerMember.getRole()).isEqualTo("OWNER");
    }

    @Test
    void addOrganisationShouldRejectMissingOwner() {
        UUID ownerId = UUID.randomUUID();
        OrganisationRequestDto request = OrganisationRequestDto.builder()
                .name("TaskForge").ownerId(ownerId).build();
        when(usersServices.getUsersEntityById(ownerId))
                .thenThrow(new UserNotFoundException("missing"));

        assertThatThrownBy(() -> organisationService.addOrganisation(request))
                .isInstanceOf(UserNotFoundException.class)
                .hasMessage("Owner doesn't exist with id: " + ownerId);
        verifyNoInteractions(organisationRepository, organisationMemberRepository, organisationMapper);
    }

    @Test
    void addOrganisationShouldTranslateDuplicateNameFailure() {
        UUID ownerId = UUID.randomUUID();
        Users owner = Users.builder().id(ownerId).name("Sahil Kumar").build();
        OrganisationRequestDto request = OrganisationRequestDto.builder()
                .name("TaskForge").ownerId(ownerId).build();
        Organisation organisation = Organisation.builder().name("TaskForge").owner(owner).build();
        when(usersServices.getUsersEntityById(ownerId)).thenReturn(owner);
        when(organisationMapper.toEntity(request, owner)).thenReturn(organisation);
        when(organisationRepository.save(organisation))
                .thenThrow(new DataIntegrityViolationException("duplicate"));

        assertThatThrownBy(() -> organisationService.addOrganisation(request))
                .isInstanceOf(InvalidOrgNameException.class)
                .hasMessage("Organisation with name : TaskForge already exists");
        verify(organisationMemberRepository, never()).save(any());
    }
}
