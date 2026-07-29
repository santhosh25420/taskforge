package com.sk.taskforge.repos;

import com.sk.taskforge.entity.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;
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
        Users user = Users.builder()
                .name("Sahil Kumar")
                .email("sahil@example.com")
                .build();

        Users savedUser = usersRepository.saveAndFlush(user);
        entityManager.clear();

        Optional<Users> result = usersRepository.findById(savedUser.getId());

        assertThat(result).isPresent();
        assertThat(result.get().getId()).isNotNull();
        assertThat(result.get().getName()).isEqualTo("Sahil Kumar");
        assertThat(result.get().getEmail()).isEqualTo("sahil@example.com");
        assertThat(result.get().getCreatedAt()).isNotNull();
        assertThat(result.get().getUpdatedAt()).isNotNull();
    }

    @Test
    void deleteByIdShouldRemovePersistedUser() {
        Users user = Users.builder()
                .name("Deleted User")
                .email("deleted@example.com")
                .build();

        Users savedUser = usersRepository.saveAndFlush(user);

        usersRepository.deleteById(savedUser.getId());
        usersRepository.flush();
        entityManager.clear();

        assertThat(usersRepository.findById(savedUser.getId())).isEmpty();
    }
}
