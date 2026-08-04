package com.sk.taskforge.controller;

import com.sk.taskforge.dto.UsersDto;
import com.sk.taskforge.service.IUsersServices;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/taskforge")
public class UsersController {

    private final IUsersServices usersServices;

    public UsersController(IUsersServices usersServices){
        this.usersServices = usersServices;
    }

    @PostMapping("/users")
    public ResponseEntity<Boolean> createUser(@RequestBody @Valid UsersDto usersDto){
        boolean result=  usersServices.addUsers(usersDto);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UsersDto>> getAllUsers(){
        List<UsersDto> result = usersServices.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/users/mail")
    public ResponseEntity<UsersDto> getByEmail(@NotBlank @RequestParam("email") String email){
        UsersDto result = usersServices.getUserByEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/users/id")
    public ResponseEntity<UsersDto> getById(@NotBlank @RequestParam("id") String id){
        try {
            UsersDto result = usersServices.getUserById(UUID.fromString(id));
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
