package com.sk.taskforge.user.mapper;

import com.sk.taskforge.common.exception.EmptyDataException;
import com.sk.taskforge.user.domain.Users;
import com.sk.taskforge.user.dto.UsersDto;
import com.sk.taskforge.user.dto.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
public class UsersMapper {

    public UsersDto convertToDto(Users users){
        if(Objects.isNull(users)){
            log.warn("Cannot convert null Users entity to UsersDto");
            throw new EmptyDataException("users cannot be null");
        }
        log.debug("Converting Users entity to UsersDto for email={}", users.getEmail());
        return UsersDto.builder()
                .name(users.getName())
                .email(users.getEmail())
                .build();
    }

    public Users converToUsers(UsersDto usersDto){
        if(Objects.isNull(usersDto)){
            log.warn("Cannot convert null UsersDto to Users entity");
            throw new EmptyDataException("usersDto cannot be null");
        }
        log.debug("Converting UsersDto to Users entity for email={}", usersDto.getEmail());
        return Users.builder()
                .name(usersDto.getName())
                .email(usersDto.getEmail())
                .build();
    }

    public UserResponse toResponse(Users users) {
        if (Objects.isNull(users)) {
            throw new EmptyDataException("users cannot be null");
        }
        return new UserResponse(
                users.getId(),
                users.getName(),
                users.getEmail(),
                users.getCreatedAt(),
                users.getUpdatedAt()
        );
    }

    /** Updates only client-editable fields; persistence-managed fields stay intact. */
    public void updateEntity(UsersDto source, Users target) {
        if (Objects.isNull(source) || Objects.isNull(target)) {
            throw new EmptyDataException("User details cannot be null");
        }
        target.setName(source.getName());
        target.setEmail(source.getEmail());
    }
}
