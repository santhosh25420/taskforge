package com.sk.taskforge.service;

import com.sk.taskforge.dto.UsersDto;
import com.sk.taskforge.exception.EmptyDataException;
import com.sk.taskforge.repos.IUsersRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verifyNoInteractions;

@ExtendWith(MockitoExtension.class)
class ImpUsersServiceTest {

    @Mock
    private IUsersRepository userRepo;

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
    void addUsersShouldReturnFalseForValidDtoWithoutPersisting() {
        UsersDto usersDto = UsersDto.builder()
                .name("Sahil Kumar")
                .email("sahil@example.com")
                .build();

        boolean result = usersService.addUsers(usersDto);

        assertThat(result).isFalse();
        verifyNoInteractions(userRepo);
    }
}
