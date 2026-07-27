package com.sk.taskforge.mapper;

import com.sk.taskforge.dto.UsersDto;
import com.sk.taskforge.entity.Users;
import com.sk.taskforge.exception.EmptyDataException;
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
}
