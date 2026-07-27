package com.sk.taskforge.service;

import com.sk.taskforge.dto.UsersDto;
import com.sk.taskforge.exception.EmptyDataException;
import com.sk.taskforge.repos.IUsersRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
public class ImpUsersService implements IUsersServices{
    private IUsersRepository userRepo;

    public ImpUsersService(IUsersRepository userRepo){
        this.userRepo = userRepo;
    }

    @Override
    public boolean addUsers(UsersDto usersDto) {
        if(Objects.isNull(usersDto)){
            throw new EmptyDataException("User Details are empty");
        }
        return false;
    }
}
