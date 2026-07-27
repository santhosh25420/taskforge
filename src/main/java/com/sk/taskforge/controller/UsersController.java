package com.sk.taskforge.controller;

import com.sk.taskforge.dto.UsersDto;
import com.sk.taskforge.service.IUsersServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/taskforge")
public class UsersController {

    private IUsersServices usersServices;

    public UsersController(IUsersServices usersServices){
        this.usersServices = usersServices;
    }

    @PostMapping("/users")
    public ResponseEntity<Boolean> createUser(@RequestBody UsersDto usersDto){
        boolean result=  usersServices.addUsers(usersDto);
        return ResponseEntity.ok(result);
    }
}
