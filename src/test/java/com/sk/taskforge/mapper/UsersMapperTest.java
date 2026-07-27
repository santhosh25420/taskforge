package com.sk.taskforge.mapper;

import com.sk.taskforge.dto.UsersDto;
import com.sk.taskforge.entity.Users;
import com.sk.taskforge.exception.EmptyDataException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UsersMapperTest {

    private final UsersMapper usersMapper = new UsersMapper();

    @Test
    void convertToDtoShouldMapUserFieldsOnly() {
        UUID id = UUID.fromString("3f8b95f0-b9d3-4eb9-b5c8-2572148ec9f1");
        LocalDateTime createdAt = LocalDateTime.of(2026, 7, 26, 10, 15);
        LocalDateTime updatedAt = LocalDateTime.of(2026, 7, 26, 11, 30);

        Users user = Users.builder()
                .id(id)
                .name("Sahil Kumar")
                .email("sahil@example.com")
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();

        UsersDto result = usersMapper.convertToDto(user);

        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("Sahil Kumar");
        assertThat(result.getEmail()).isEqualTo("sahil@example.com");
    }

    @Test
    void converToUsersShouldMapDtoFieldsOnly() {
        UsersDto usersDto = UsersDto.builder()
                .name("Sahil Kumar")
                .email("sahil@example.com")
                .build();

        Users result = usersMapper.converToUsers(usersDto);

        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("Sahil Kumar");
        assertThat(result.getEmail()).isEqualTo("sahil@example.com");
        assertThat(result.getId()).isNull();
        assertThat(result.getCreatedAt()).isNull();
        assertThat(result.getUpdatedAt()).isNull();
    }

    @Test
    void convertToDtoShouldFailFastWhenUserIsNull() {
        assertThatThrownBy(() -> usersMapper.convertToDto(null))
                .isInstanceOf(EmptyDataException.class)
                .hasMessage("users cannot be null");
    }

    @Test
    void converToUsersShouldFailFastWhenDtoIsNull() {
        assertThatThrownBy(() -> usersMapper.converToUsers(null))
                .isInstanceOf(EmptyDataException.class)
                .hasMessage("usersDto cannot be null");
    }
}
