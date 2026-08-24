package com.sk.taskforge.service;

import com.sk.taskforge.dto.UsersDto;
import com.sk.taskforge.dto.UserResponse;
import com.sk.taskforge.entity.Users;

import java.util.List;
import java.util.UUID;

public interface IUsersServices {

    public boolean addUsers(UsersDto usersDto);
    public UsersDto getUserByEmail(String email);
    public UsersDto getUserById(UUID id);
    public boolean deleteUserById(UUID id);
    public List<UsersDto> getAll();
    UserResponse updateUser(UUID id, UsersDto usersDto);
    public Users getUsersEntityById(UUID id);
}
