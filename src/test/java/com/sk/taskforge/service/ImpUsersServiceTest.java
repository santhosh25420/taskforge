package com.sk.taskforge.service;

import com.sk.taskforge.dto.UsersDto;
import com.sk.taskforge.dto.UserResponse;
import com.sk.taskforge.entity.Users;
import com.sk.taskforge.exception.EmptyDataException;
import com.sk.taskforge.exception.UserAlreadyExistsException;
import com.sk.taskforge.exception.UserNotFoundException;
import com.sk.taskforge.mapper.UsersMapper;
import com.sk.taskforge.repos.IUsersRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ImpUsersServiceTest {

    @Mock
    private IUsersRepository userRepo;

    @Mock
    private UsersMapper usersMapper;

    @InjectMocks
    private ImpUsersService usersService;

    @Test
    void addUsersShouldRejectNullDto() {
        assertThatThrownBy(() -> usersService.addUsers(null))
                .isInstanceOf(EmptyDataException.class)
                .hasMessage("User Details are empty");

        verifyNoInteractions(userRepo);
    }

    @Test
    void addUsersShouldReturnTrueAndPersistValidDto() {
        UsersDto usersDto = UsersDto.builder()
                .name("Sahil Kumar")
                .email("sahil@example.com")
                .build();

        Users mappedUser = Users.builder()
                .name("Sahil Kumar")
                .email("sahil@example.com")
                .build();

        when(usersMapper.converToUsers(usersDto)).thenReturn(mappedUser);

        boolean result = usersService.addUsers(usersDto);

        assertThat(result).isTrue();
        verify(usersMapper).converToUsers(usersDto);
        verify(userRepo).save(mappedUser);
    }

    @Test
    void updateUserShouldPersistEditableFieldsAndReturnResponse() {
        UUID id = UUID.randomUUID();
        UsersDto request = UsersDto.builder().name("Updated Name").email("updated@example.com").build();
        Users existing = Users.builder().id(id).name("Original Name").email("original@example.com").build();
        UserResponse expected = new UserResponse(id, "Updated Name", "updated@example.com", null, null);
        when(userRepo.findById(id)).thenReturn(Optional.of(existing));
        when(userRepo.existsByEmailAndIdNot(request.getEmail(), id)).thenReturn(false);
        when(userRepo.save(existing)).thenReturn(existing);
        when(usersMapper.toResponse(existing)).thenReturn(expected);

        UserResponse result = usersService.updateUser(id, request);

        assertThat(result).isEqualTo(expected);
        verify(usersMapper).updateEntity(request, existing);
        verify(userRepo).save(existing);
    }

    @Test
    void updateUserShouldFailWhenUserDoesNotExist() {
        UUID id = UUID.randomUUID();
        when(userRepo.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> usersService.updateUser(id, UsersDto.builder().name("Name").email("name@example.com").build()))
                .isInstanceOf(UserNotFoundException.class);
        verify(userRepo, never()).save(any());
    }

    @Test
    void updateUserShouldRejectAnEmailUsedByAnotherUser() {
        UUID id = UUID.randomUUID();
        UsersDto request = UsersDto.builder().name("Name").email("taken@example.com").build();
        when(userRepo.findById(id)).thenReturn(Optional.of(Users.builder().id(id).build()));
        when(userRepo.existsByEmailAndIdNot(request.getEmail(), id)).thenReturn(true);

        assertThatThrownBy(() -> usersService.updateUser(id, request))
                .isInstanceOf(UserAlreadyExistsException.class);
        verify(userRepo, never()).save(any());
    }
}
