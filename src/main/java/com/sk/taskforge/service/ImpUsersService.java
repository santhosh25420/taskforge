package com.sk.taskforge.service;

import com.sk.taskforge.dto.UsersDto;
import com.sk.taskforge.dto.UserResponse;
import com.sk.taskforge.entity.Users;
import com.sk.taskforge.exception.EmptyDataException;
import com.sk.taskforge.exception.UserNotFoundException;
import com.sk.taskforge.exception.UserAlreadyExistsException;
import com.sk.taskforge.mapper.UsersMapper;
import com.sk.taskforge.repos.IUsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.UUID;


@Service
@Slf4j
public class ImpUsersService implements IUsersServices{
    private IUsersRepository userRepo;
    private UsersMapper mapper;

    public ImpUsersService(IUsersRepository userRepo,UsersMapper mapper){
        this.userRepo = userRepo;
        this.mapper = mapper;
    }

    @Override
    public boolean addUsers(UsersDto usersDto) {
        if(Objects.isNull(usersDto)){
            log.error("Empty input data received");
            throw new EmptyDataException("User Details are empty");
        }else{
            Users newUsers = mapper.converToUsers(usersDto);
            userRepo.save(newUsers);
            return true;
        }

    }

    @Override
    public UsersDto getUserByEmail(String email) {
        Users  users = userRepo.findByEmail(email).orElseThrow(()->{
            log.error("User not found with email: {}", email);
            throw new UserNotFoundException("User not found with mail: "+email);

        });
        return mapper.convertToDto(users);
    }

    @Override
    public UsersDto getUserById(UUID id) {
        Users users = userRepo.findById(id).orElseThrow(()->{
            log.error("User not found with id: "+id);
                throw new UserNotFoundException("User not found with id: "+id);
        });
        return mapper.convertToDto(users);
    }

    @Override
    public boolean deleteUserById(UUID id) {
        userRepo.deleteById(id);
        return true;
    }

    @Override
    public List<UsersDto> getAll() {
        List<Users> usersList = userRepo.findAll();
        if(usersList.isEmpty()){
            log.error("No users found");
            throw new UserNotFoundException("Empty list of users");
        }
        return usersList.stream().map(users-> mapper.convertToDto(users)).toList();
    }

    @Override
    @Transactional
    public UserResponse updateUser(UUID id, UsersDto usersDto) {
        if (Objects.isNull(usersDto)) {
            throw new EmptyDataException("User details are empty");
        }

        Users user = userRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

        if (userRepo.existsByEmailAndIdNot(usersDto.getEmail(), id)) {
            throw new UserAlreadyExistsException("A user already exists with email: " + usersDto.getEmail());
        }

        mapper.updateEntity(usersDto, user);
        Users savedUser = userRepo.save(user);
        return mapper.toResponse(savedUser);
    }

    @Override
    public Users getUsersEntityById(UUID id){
        return userRepo.findById(id).orElseThrow(()->
                new UserNotFoundException("User with id: "+id+" doesn't exists"));
    }
}
