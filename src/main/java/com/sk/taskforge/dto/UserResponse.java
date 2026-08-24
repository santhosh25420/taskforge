package com.sk.taskforge.dto;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * The representation returned to API clients after a user is read or changed.
 * Keeping it separate from request DTOs prevents client input from controlling
 * server-managed fields such as the identifier and audit timestamps.
 */
public record UserResponse(
        UUID id,
        String name,
        String email,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
