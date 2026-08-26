package com.sk.taskforge.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import java.time.LocalDateTime;
import java.util.UUID;

@Table(
        name = "users",
        uniqueConstraints = @UniqueConstraint(
                name = "uq_users_email",
                columnNames = {"email"}
        )
)
@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Users {

    @Id
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email" , nullable = false)
    private String email;

    @CreationTimestamp
    @Column(nullable=false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    private void assignId() {
        if (id == null) {
            id = UUID.randomUUID();
        }
    }

}
