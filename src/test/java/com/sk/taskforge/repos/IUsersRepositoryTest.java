package com.sk.taskforge.repos;

import com.sk.taskforge.entity.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestPropertySource(properties = {
        "spring.liquibase.enabled=false",
        "spring.jpa.hibernate.ddl-auto=create-drop",
        "spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect"
})
class IUsersRepositoryTest {

    @Autowired
    private IUsersRepository usersRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void saveShouldPersistAndFindUserById() {
        UUID userId = UUID.fromString("3f8b95f0-b9d3-4eb9-b5c8-2572148ec9f1");
        Users user = Users.builder()
                .id(userId)
                .name("Sahil Kumar")
                .email("sahil@example.com")
                .build();

        usersRepository.saveAndFlush(user);
        entityManager.clear();

        Optional<Users> result = usersRepository.findById(userId);

        assertThat(result).isPresent();
        assertThat(result.get().getName()).isEqualTo("Sahil Kumar");
        assertThat(result.get().getEmail()).isEqualTo("sahil@example.com");
        assertThat(result.get().getCreatedAt()).isNotNull();
        assertThat(result.get().getUpdatedAt()).isNotNull();
    }

    @Test
    void deleteByIdShouldRemovePersistedUser() {
        UUID userId = UUID.fromString("066f3d31-6df5-4b2a-a0e4-d344b6a07121");
        Users user = Users.builder()
                .id(userId)
                .name("Deleted User")
                .email("deleted@example.com")
                .build();

        usersRepository.saveAndFlush(user);

        usersRepository.deleteById(userId);
        usersRepository.flush();
        entityManager.clear();

        assertThat(usersRepository.findById(userId)).isEmpty();
    }
}
