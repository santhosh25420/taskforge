package com.sk.taskforge.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UsersDto {

    @NotBlank(message = "Name shouldn't be blank")
    @Size(min = 2, max = 100, message = "Name should not be greater than 100 and lesser than 2")
    private String name;

    @NotBlank(message = "Email should not be blank")
    @Email(message = "Should be a valid email")
    private String email;
}
