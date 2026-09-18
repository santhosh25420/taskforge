package com.sk.taskforge.user.persistence;

import com.sk.taskforge.user.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IUsersRepository extends JpaRepository<Users, UUID> {

    public Optional<Users> findByEmail(String email);

    boolean existsByEmailAndIdNot(String email, UUID id);
}
