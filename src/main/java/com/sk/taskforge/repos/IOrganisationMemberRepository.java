package com.sk.taskforge.repos;

import com.sk.taskforge.entity.OrganisationMembers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IOrganisationMemberRepository extends JpaRepository<OrganisationMembers, UUID> {
}
