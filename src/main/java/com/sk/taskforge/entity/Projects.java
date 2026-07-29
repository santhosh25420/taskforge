package com.sk.taskforge.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(
        name = "projects",
        uniqueConstraints = @UniqueConstraint(
                name = "slug_unique",
                columnNames = {"slug"}
        )
)
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Projects {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="id", nullable = false, updatable = false)
    private UUID id;

    @Column(name ="title", nullable = false, length = 320)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="organisation_id", nullable = false)
    private Organisation organisation;

    @Column(name = "slug", length = 250)
    private String slug;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;
}
