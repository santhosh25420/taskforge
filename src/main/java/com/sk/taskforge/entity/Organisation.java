package com.sk.taskforge.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.text.Normalizer;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(
        name = "organisations",
        uniqueConstraints = {@UniqueConstraint(
                name = "organisation_slug_unique",
                columnNames = {"slug"}
        ),
                @UniqueConstraint(
                        name = "UK_organisation_name_owner_id",
                        columnNames = {"owner_id","name"}
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Organisation {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "name", nullable=false)
    private String name;

    @Column(name = "slug", length = 250)
    private String slug;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private Users owner;

    @CreationTimestamp
    @Column(nullable=false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    private void assignId() {
        if (id == null) {
            this.id = UUID.randomUUID();
        }
        if(slug == null){
            this.slug = Normalizer.normalize(this.name, Normalizer.Form.NFD)
                    .replaceAll("\\p{M}", "")       // Remove accents
                    .toLowerCase(Locale.ROOT)
                    .replaceAll("[^a-z0-9\\s-]", "") // Remove special characters
                    .trim()
                    .replaceAll("\\s+", "-")        // Spaces to hyphens
                    .replaceAll("-+", "-")
                    + "-" + Objects.hash(this.id);

        }
    }


}
