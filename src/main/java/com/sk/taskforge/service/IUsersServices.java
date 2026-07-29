package com.sk.taskforge.service;

import com.sk.taskforge.dto.UsersDto;

import java.util.UUID;

public interface IUsersServices {

    public boolean addUsers(UsersDto usersDto);
    public UsersDto getUserByEmail(String email);
    public UsersDto getUserById(UUID id);
    public boolean deleteUserById(UUID id);
}
