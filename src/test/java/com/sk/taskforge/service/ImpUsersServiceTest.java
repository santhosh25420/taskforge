package com.sk.taskforge.service;

import com.sk.taskforge.dto.UsersDto;
import com.sk.taskforge.entity.Users;
import com.sk.taskforge.exception.EmptyDataException;
import com.sk.taskforge.mapper.UsersMapper;
import com.sk.taskforge.repos.IUsersRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.UUID;

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
}
