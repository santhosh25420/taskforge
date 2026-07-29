package com.sk.taskforge.repos;

import com.sk.taskforge.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IUsersRepository extends JpaRepository<Users, UUID> {

    public Users findByEmail(String email);
}
