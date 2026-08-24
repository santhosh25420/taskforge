package com.sk.taskforge.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OrganisationRequestDto {

    @NotBlank(message = "Organisation name can\'t be null")
    private String name;

    @NotBlank(message = "Organisation owner can\'t be null")
    private UUID ownerId;
}
